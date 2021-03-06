package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.dao.InsertDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import jp.co.froide.exercise.TeamCoffein.validation.EmpFormValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InsertEmpController {

    @Autowired
    InsertDao insertDao;
    @Autowired
    PostDao postDao;
    @Autowired
    DeptDao deptDao;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmpFormValidator empFormValidator;

    @InitBinder("employeeForm")
    public void validatorBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(empFormValidator);
    }


    @GetMapping("/emp/create")
    public String showForm(Model model) {
        if (!model.containsAttribute("error")) {
            EmployeeForm form = new EmployeeForm();
            model.addAttribute("form", new EmployeeForm());
        }
        List<Post> postList;
        List<Department> deptList;
        try {
            postList = postDao.selectAll();
            deptList = deptDao.selectAll();
        }catch (Exception e){
            return "dberror";
        }
        model.addAttribute("postList", postList);
        model.addAttribute("deptList", deptList);
        model.addAttribute("noSelect", null);
        return "createForm";
    }

    @PostMapping(value = "/emp/create", params = "action=cancel")
    public String cancelInsert() {
        return "redirect:/emp";
    }

    @PostMapping(value = "/emp/create", params = "action=insert")
    public String createEmp(@Validated @ModelAttribute EmployeeForm form, BindingResult result,
                            Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.form", result);
            ra.addFlashAttribute("error", "this has errors");
            ra.addFlashAttribute("form", form);
            return "redirect:/emp/create";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        String str_nowDate = format.format(nowDate);

        PostEmployee emp = new PostEmployee();
        emp.cloneForm(form);

        emp.setDelete_flag(0);
        emp.setUpdate_at(str_nowDate);
        emp.setCreate_at(str_nowDate);
        if (form.getAuth() == 0) {
            String pass = RandomStringUtils.randomAlphanumeric(6);
            PostEmployee inserted_emp;
            try {
                String hashed_pass = passwordEncoder.encode(pass);
                emp.setPassword(hashed_pass);
                insertDao.insert(emp);
                inserted_emp = employeeDao.selectByEmail(emp.getEmail());
            }catch (Exception e){
                return "dberror";
            }
            ra.addFlashAttribute("emp_id", inserted_emp.getEmp_id());
            ra.addFlashAttribute("emp", emp);
            ra.addFlashAttribute("pass", pass);
            return "redirect:/emp/create/asAdministrator";
        } else {
            emp.setPassword("0");
            PostEmployee inserted_emp;
            try {
                insertDao.insert(emp);
                inserted_emp = employeeDao.selectByEmail(emp.getEmail());
            }catch (Exception e){
                return "dberror";
            }
            Integer id = inserted_emp.getEmp_id();
            return "redirect:/emp/edit/" + id;
        }
    }

    @GetMapping("/emp/create/asAdministrator")
    public String showasAdmin(Model model){
        System.out.println("A");
        if(!model.containsAttribute("emp")){return "redirect:/emp/create";}
        return "confirmAsAdmin";
    }
}
