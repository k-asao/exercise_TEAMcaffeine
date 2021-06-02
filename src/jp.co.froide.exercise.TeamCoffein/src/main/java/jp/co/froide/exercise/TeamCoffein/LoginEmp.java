package jp.co.froide.exercise.TeamCoffein;

import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;


public class LoginEmp extends User {
    public LoginEmp(PostEmployee emp, Collection<? extends GrantedAuthority> authorities){
        super(emp.getEmail(), emp.getPassword(), authorities);
    }
}
