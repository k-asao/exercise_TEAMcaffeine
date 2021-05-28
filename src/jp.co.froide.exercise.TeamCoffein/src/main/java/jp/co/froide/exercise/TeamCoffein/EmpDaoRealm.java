package jp.co.froide.exercise.TeamCoffein;

import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Optional.*;

import java.util.List;
import java.util.Set;
import java.util.logging.ErrorManager;

@Component
@Slf4j
public class EmpDaoRealm implements UserDetailsService {

    @Autowired
    EmployeeDao empDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        List<GrantedAuthority> authorityList = null;

        try{
            Employee emp = empDao.selectByEmail(email);

            if(emp == null){
                System.out.println("dame");
                throw new  UsernameNotFoundException("account not found [id=" + email + "]");
            }
            System.out.println(emp.getName());
            /*pass = emp.getPass().orElseThrow(() -> new  UsernameNotFoundException("You are not Administrator"));*/
            Set<String> roleKeys = new HashSet<>();
            Set<String> permissionKey = new HashSet<>();

            Set<String> authorities = new HashSet<>();
            authorities.addAll(roleKeys);
            authorities.addAll(permissionKey);
            authorityList = AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));

            return new LoginEmp(emp, authorityList);

        }catch (Exception e){
            if(!(e instanceof UsernameNotFoundException)){
                System.out.print("a");
                log.error("failed to getLoginUser. ", e);
                throw e;
            }

            throw new UsernameNotFoundException("could not select staff.", e);
        }
    }
}
