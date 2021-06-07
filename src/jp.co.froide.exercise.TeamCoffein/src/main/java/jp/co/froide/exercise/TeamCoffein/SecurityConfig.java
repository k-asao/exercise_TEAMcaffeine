package jp.co.froide.exercise.TeamCoffein;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    private static final String REMEMBER_ME_KEY =  "remKey";

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
            .antMatchers("/","/emp","/loginFail","/emp/forgetPass", "/emp/changePass","/emp/sucChange","/*.csv","/css/**","/emp/removed","/emp/removed/{id}","/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling();

        http.formLogin()
            .loginPage("/emp/login")
            .loginProcessingUrl("/processing")
            .successForwardUrl("/success")
            .failureUrl("/loginFail")
            .usernameParameter("login_id")
            .passwordParameter("password").permitAll();

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/emp")
            .invalidateHttpSession(true).permitAll();
        http.rememberMe()
            .key("remkey")
            .rememberMeCookieName("name")
            .tokenValiditySeconds(86400)
            .useSecureCookie(true);
    }

}
