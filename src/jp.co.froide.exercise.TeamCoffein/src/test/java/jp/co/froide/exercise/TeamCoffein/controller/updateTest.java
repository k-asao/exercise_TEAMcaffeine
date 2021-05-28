package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@SpringBootTest
public class updateTest {
    @Autowired
    Validator validator;
    @Mock
    UpdateDao updateDao;
    @Mock
    PostDao postDao;
    @Mock
    DeptDao deptDao;
    @Mock
    Model model;
    @Mock
    RedirectAttributes ra;

    @InjectMocks
    UpdateEmpController updateEmpController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void updateFormTest(){
        EmployeeForm form = new EmployeeForm();
        BindingResult result = new BindException(form,"insform");
        String errorStringByLength = "";
        for(int i=0; i<255; i++){
            errorStringByLength+="ア";
        }
        form.setName("あさお");
        form.setKana(errorStringByLength);
        form.setHire_date("2012-11-13");
        form.setPost_id(2);
        form.setDept_id(4);
        form.setTel("02211114444");
        form.setEmail("abc@example.co.jp");

        validator.validate(form,result);

        PostEmployee emp = new PostEmployee();
        emp.setName(form.getName());
        emp.setKana(form.getKana());
        emp.setHire_date(form.getHire_date());
        emp.setPost_id(form.getPost_id());
        emp.setDept_id(form.getDept_id());
        emp.setTel(form.getTel());
        emp.setEmail(form.getEmail());

        Mockito.when(updateDao.update(emp)).thenReturn(1);
        String expected = updateEmpController.updateEmp(form, result, model, ra, 2);
        //下２つはそれぞれ入力ミス時と成功時、テストするモックに合わせて適宜コメントオフ切替
        assertThat(expected, is("redirect:/emp/edit/{id}"));
        //assertThat(expected, is("redirect:/emp"));


    }

}

