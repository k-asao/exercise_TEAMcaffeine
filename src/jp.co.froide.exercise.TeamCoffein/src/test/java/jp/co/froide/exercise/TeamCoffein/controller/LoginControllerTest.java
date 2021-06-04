package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.form.ConfMailForm;
import jp.co.froide.exercise.TeamCoffein.form.ConfPassForm;
import jp.co.froide.exercise.TeamCoffein.validation.MailFormValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest
public class LoginControllerTest {
    @Autowired
    Validator validator;
    @Autowired
    MailFormValidator mailFormValidator;
    @Mock
    UpdateDao updateDao;
    @Mock
    EmployeeDao employeeDao;
    @Mock
    Model model;
    @Mock
    RedirectAttributes ra;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    PostEmployee emp;
    @Mock
    BindingResult result;


    @InjectMocks
    LoginController loginController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public  void successChangePassTest(){
        ConfPassForm passForm = new ConfPassForm();
        emp.setPassword("a");
        emp.setEmp_id(12);
        passForm.setEmail("emp1111@ex.co.jp");
        passForm.setPass("123459");
        passForm.setPassConf("123456");
        if(passForm.getPass().length() < 6 || passForm.getPass().length() > 73) Mockito.when(result.hasErrors()).thenReturn(true);
        if(passForm.getPassConf().length() < 6) Mockito.when(result.hasErrors()).thenReturn(true);
        if(!passForm.getPass().equals(passForm.getPassConf())) Mockito.when(result.hasErrors()).thenReturn(true);
        Mockito.when(employeeDao.selectByEmail(passForm.getEmail())).thenReturn(emp);
        Mockito.when(passwordEncoder.encode(passForm.getPass())).thenReturn("aaaa");
        String expected = loginController.successChangePass(passForm, result, model, ra);
        assertThat(expected, is("redirect:/emp/changePass"));
    }

    @Test
    public void checkMailTest(){
        ConfMailForm cmf = new ConfMailForm();
        cmf.setEmail("aaab@gmail.com");
        String[] existing_address = {"aaaa@gmail.com", "bbbb@gmail.com"};
        //メアドが存在しない場合
        if(!Arrays.asList(existing_address).contains(cmf.getEmail())) Mockito.when(result.hasErrors()).thenReturn(true);
        //メアドが未入力
        if(cmf.getEmail().length()==0) Mockito.when(result.hasErrors()).thenReturn(true);
        String expected = loginController.changePass(cmf, result, model, ra);
        assertThat(expected, is("redirect:/emp/forgetPass"));
    }
}
