package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.SecurityConfig;
import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.InsertDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import jp.co.froide.exercise.TeamCoffein.validation.EmpFormValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        List<Post> postList = postDao.selectAll();
        List<Department> deptList = deptDao.selectAll();
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
            System.out.println(result);
            return "redirect:/emp/create";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        String str_nowDate = format.format(nowDate);

        PostEmployee emp = new PostEmployee();
        emp.setName(form.getName());
        emp.setKana(form.getKana());
        emp.setHire_date(form.getHire_date());
        emp.setPost_id(form.getPost_id());
        emp.setDept_id(form.getDept_id());
        emp.setTel(form.getTel());
        emp.setEmail(form.getEmail());
        emp.setDelete_flag(0);
        emp.setUpdate_at(str_nowDate);
        emp.setCreate_at(str_nowDate);
        if (form.getAuth() == 0) {
            String pass = RandomStringUtils.randomAlphanumeric(6);
            String hashed_pass = passwordEncoder.encode(pass);
            emp.setPassword(hashed_pass);
            insertDao.insert(emp);
            model.addAttribute("emp", emp);
            model.addAttribute("pass", pass);
            return "confirmAsAdmin";
        } else {
            emp.setPassword("0");
            insertDao.insert(emp);
            return "redirect:/emp";
        }
    }
}
