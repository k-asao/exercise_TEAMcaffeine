package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class UpdateEmpController {
    @Autowired
    UpdateDao updateDao;
    @Autowired
    PostDao postDao;
    @Autowired
    DeptDao deptDao;


    @GetMapping("/emp/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Integer id) throws ParseException {
        if (!model.containsAttribute("error")) {
            PostEmployee emp = updateDao.selectEmpByID(id);
            EmployeeForm form = new EmployeeForm();
            form.setName(emp.getName());
            form.setKana(emp.getKana());
            form.setHire_date(emp.getHire_date());
            form.setPost_id(emp.getPost_id());
            form.setDept_id(emp.getDept_id());
            form.setTel(emp.getTel());
            form.setEmail(emp.getEmail());

//            変更点
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date nowDate = new Date();
            Date date = java.sql.Date.valueOf(emp.getHire_date());
            TimeUnit diff = TimeUnit.DAYS;
            Long result = nowDate.getTime() - date.getTime();
            Long yearDiff = diff.convert(result, TimeUnit.MILLISECONDS) / 365;
            model.addAttribute("year", yearDiff);

            model.addAttribute("form", form);
        }

        List<Post> postList = postDao.selectAll();
        List<Department> deptList = deptDao.selectAll();
        model.addAttribute("postList", postList);
        model.addAttribute("deptList", deptList);
        model.addAttribute("id", id);
        return "detailEmp";
    }

    @RequestMapping(value = "/emp/edit/{id}", params = "action=cancel")
    public String cancelUpdate() {
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

       PostEmployee emp = new PostEmployee();
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
    public String delEmp(EmployeeForm form, @PathVariable("id") Integer id) {
        PostEmployee emp = updateDao.selectEmpByID(id);
        updateDao.delete(emp);
        return "redirect:/emp";
    }

}