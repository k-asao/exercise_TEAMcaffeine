package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UpdateEmpController {
    @Autowired
    UpdateDao updateDao;
    @Autowired
    PostDao postDao;
    @Autowired
    DeptDao deptDao;

    @GetMapping("/emp")
    public String showList(Model model){
        return "list";
    }

    @GetMapping("/emp/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Integer id){
        Employee emp = updateDao.selectEmpByID(id);
        EmployeeForm form = new EmployeeForm();
        form.setName(emp.getName());
        form.setKana(emp.getKana());
        form.setHire_date(emp.getHire_date());
        form.setPost_id(emp.getPost_id());
        form.setDept_id(emp.getDept_id());
        form.setTel(emp.getTel());
        form.setEmail(emp.getEmail());

        List<Post> postList = postDao.selectAll();
        List<Department> deptList = deptDao.selectAll();
        model.addAttribute("postList", postList);
        model.addAttribute("deptList", deptList);
        model.addAttribute("form",form);
        model.addAttribute("id", id);
        System.out.print(form);

        return "detailEmp";


    }

    @RequestMapping(value = "/emp/edit/{id}", params = "action=cancel")
    public String cancelUpdate(){
        return "redirect:/emp";
    }

    @RequestMapping(value = "/emp/edit/{id}", params = "action=update")
    public String updateEmp(@Validated @ModelAttribute EmployeeForm form, BindingResult result,
                            Model model, RedirectAttributes ra,@PathVariable("id") Integer id){
       if(result.hasErrors()){
           ra.addFlashAttribute("org.springframework.validation.BindingResult.form", result);
           ra.addFlashAttribute("error", "this has errors");
           ra.addFlashAttribute("form", form);
           return "redirect:/emp/edit/{id}";
       }
       System.out.print("sssssss");
       Employee emp = new Employee();
       emp.setEmp_id(id);
       emp.setName(form.getName());
       emp.setKana(form.getKana());
       emp.setHire_date(form.getHire_date());
       emp.setPost_id(form.getPost_id());
       emp.setDept_id(form.getDept_id());
       emp.setTel(form.getTel());
       emp.setEmail(form.getEmail());
       updateDao.update(emp);
       return "redirect:/emp";
    }


    @RequestMapping("/emp/edit/{id}/delete")
    public String delEmp(EmployeeForm form, @PathVariable("id") Integer id){
        Employee emp = updateDao.selectEmpByID(id);
        updateDao.delete(emp);
        return "redirect:/emp";
    }


}