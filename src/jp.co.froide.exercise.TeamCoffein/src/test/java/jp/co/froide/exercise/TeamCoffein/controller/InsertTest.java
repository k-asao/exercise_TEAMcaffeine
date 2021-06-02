package jp.co.froide.exercise.TeamCoffein.controller;


import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.InsertDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.seasar.doma.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


@SpringBootTest
public class InsertTest {

    @Autowired
    Validator validator;
    @Mock
    InsertDao insertDao;
    @Mock
    PostDao postDao;
    @Mock
    DeptDao deptDao;
    @Mock
    Model model;
    @Mock
    RedirectAttributes ra;

    @InjectMocks
    InsertEmpController insertEmpController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void insertFormTest(){
        EmployeeForm form = new EmployeeForm();
        BindingResult result = new BindException(form,"insform");
        String errorStringByLength = "";
        for(int i=0; i<256; i++){
            errorStringByLength+="あ";
        }
        form.setName(errorStringByLength);
        form.setKana("アサオ");
        form.setHire_date("2012-11-13");
        form.setPost_id(2);
        form.setDept_id(4);
        form.setTel("02211114444");
        form.setEmail("abc@example.co.jp");

        validator.validate(form,result);

        String expected = insertEmpController.createEmp(form, result, model, ra);
        assertThat(expected, is("redirect:/emp"));


    }

    @Test
    public void cancelTest(){

    }


}
