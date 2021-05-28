package jp.co.froide.exercise.TeamCoffein.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.froide.exercise.TeamCoffein.dao.UserDao;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import jp.co.froide.exercise.TeamCoffein.form.SearchForm;
//import jp.co.froide.exercise.TeamCoffein.validation.EmpFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/")
public class JobMemberController {

    @Autowired
    UserDao userDao;
    /*@Autowired
    EmpFormValidator empFormValidator;

    //html↓
    @InitBinder("employeeForm")
    public void validatorBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(empFormValidator);
    }*/

    //全件取得
    @GetMapping("/emp")
    public String userList(Model model) {
        Collection<Employee> empDataList  = userDao.selectEmpAll();
        model.addAttribute("emp", empDataList);
        model.addAttribute("searchForm", new SearchForm());
        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("noSelect", null);
        return "jobMemberForm";
    }

    //検索
    @PostMapping("/emp")
    public String search(SearchForm form, Model model) {
        String order = form.getOrder();
        String name = form.getName();
        Integer post_name = form.getPost_id();
        Integer dept_name = form.getDept_id();
        String hire_date = form.getHire_date();
        Collection<Employee> List = userDao.selectSearchAll(order, name, post_name, dept_name, hire_date);
        model.addAttribute("searchForm", form);
        model.addAttribute("emp", List);
        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("noSelect", null);
        return "jobMemberForm";
    }

}
