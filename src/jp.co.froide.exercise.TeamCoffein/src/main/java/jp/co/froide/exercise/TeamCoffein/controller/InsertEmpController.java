package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.InsertDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class InsertEmpController {

    @Autowired
    InsertDao insertDao;
    @Autowired
    PostDao postDao;
    @Autowired
    DeptDao deptDao;

    @GetMapping("/emp/create")
    public String showForm(Model model){
        if(!model.containsAttribute("error")) {
            EmployeeForm form = new EmployeeForm();
            model.addAttribute("form", new EmployeeForm());
        }
        List<Post> postList = postDao.selectAll();
        List<Department> deptList = deptDao.selectAll();
        model.addAttribute("postList", postList);
        model.addAttribute("deptList", deptList);
        return "createForm";
    }


    @PostMapping("/emp/create")
    public String createEmp(@Validated @ModelAttribute EmployeeForm form, BindingResult result,
                            Model model, RedirectAttributes ra){
        if(result.hasErrors()){
            ra.addFlashAttribute("org.springframework.validation.BindingResult.form", result);
            ra.addFlashAttribute("error", "this has errors");
            ra.addFlashAttribute("form", form);
            System.out.println(form);
            return "redirect:/emp/create";
        }

        Employee emp = new Employee();
        emp.setName(form.getName());
        emp.setKana(form.getKana());
        emp.setHire_date(form.getHire_date());
        emp.setPost_id(form.getPost_id());
        emp.setDept_id(form.getDept_id());
        emp.setTel(form.getTel());
        emp.setEmail(form.getEmail());
        System.out.print(form);
        insertDao.insert(emp);
        return "/emp";
    }

}
