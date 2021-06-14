package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.DeptDao;
import jp.co.froide.exercise.TeamCoffein.dao.InsertDao;
import jp.co.froide.exercise.TeamCoffein.dao.PostDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.entity.*;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
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
    @Autowired
    InsertDao insertDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    EmpFormValidator empFormValidator;

    @InitBinder("employeeForm")
    public void validatorBinder(WebDataBinder webDataBinder) {webDataBinder.addValidators(empFormValidator);}


    @GetMapping("/emp/edit/{id}")
    public String showEditForm(Model model, @PathVariable("id") Integer id) throws ParseException {
        if (!model.containsAttribute("error")) {
            PostEmployee emp;
            try {
                emp = updateDao.selectEmpByID(id);
            }catch (Exception e){
                return "dberror";
            }
            EmployeeForm form = new EmployeeForm();
            form.cloneEmp(emp);

            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-mm-dd");
            Date nowDate = new Date();
            Date date = java.sql.Date.valueOf(emp.getHire_date());
            TimeUnit diff = TimeUnit.DAYS;
            Long result = nowDate.getTime() - date.getTime();
            Long yearDiff = diff.convert(result, TimeUnit.MILLISECONDS) / 365;
            model.addAttribute("year", yearDiff);

            model.addAttribute("form", form);
        }
        if(!model.containsAttribute("successMessage")){
            model.addAttribute("successMessage", null);
        }
        Collection<Post> postList;
        Collection<Department> deptList;
        try {
            postList = postDao.selectAll();
            deptList = deptDao.selectAll();
        }catch (Exception e){
            return "dberror";

        }
        model.addAttribute("noSelect", null);
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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        String str_nowDate = format.format(nowDate);


        PostEmployee emp ;
        EmpHistory empHis = new EmpHistory();
        try {
            emp = updateDao.selectEmpByID(id);
            empHis.cloneEmp(emp);
            empHis.setInsert_history_at(str_nowDate);

            insertDao.insert(empHis);
            if (updateDao.count(id) > 20) {
                updateDao.deleteOver20(id);
            }
        }catch (Exception e){
            return "dberror";
        }

        emp.cloneForm(form);
        emp.setEmp_id(id);
        emp.setUpdate_at(str_nowDate);

        if (form.getAuth() == 0 && empHis.getPassword().equals("0")) {
            String pass = RandomStringUtils.randomAlphanumeric(6);
            try {
                String hashed_pass = passwordEncoder.encode(pass);
                emp.setPassword(hashed_pass);
                updateDao.update(emp);
            }catch (Exception e){
                return "dberror";

            }
            model.addAttribute("emp_id", emp.getEmp_id());
            model.addAttribute("emp", emp);
            model.addAttribute("pass", pass);
            return "confirmAsAdmin";
        } else if(form.getAuth() == 0 && !empHis.getPassword().equals("0")){
            try {
                updateDao.update(emp);
            }catch (Exception e){
                return "dberror";

            }
            ra.addFlashAttribute("successMessage", "success");
            return "redirect:/emp/edit/{id}";
        }else {
            emp.setPassword("0");
            try {
                updateDao.update(emp);
            }catch (Exception e){
                return "dberror";
            }
            ra.addFlashAttribute("successMessage", "success");
            return "redirect:/emp/edit/{id}";
        }
    }

    @RequestMapping("/emp/edit/{id}/delete")
    public String delEmp(EmployeeForm form, @PathVariable("id") Integer id) {
        try {
            PostEmployee emp = updateDao.selectEmpByID(id);
            emp.setDelete_flag(1);
            updateDao.update(emp);
        }catch (Exception e){
            return "dberror";
        }
        return "redirect:/emp";
    }

    @GetMapping("/emp/edit/{id}/history")
    public String showHistory(Model model, @PathVariable("id") Integer id){
        List<EmpHisReceive> empHis;
        try {
            empHis = updateDao.selectEmpHisById(id);
        }catch (Exception e){
            return "dberror";
        }
        model.addAttribute("id", id);
        model.addAttribute("history", empHis);
        return "empHistory";
    }


}
