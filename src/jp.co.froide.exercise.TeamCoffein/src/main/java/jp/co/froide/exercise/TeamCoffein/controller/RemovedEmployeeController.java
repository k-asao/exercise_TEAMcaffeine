package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.RemovedEmployeeDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.dao.UserDao;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.form.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

@Controller
public class RemovedEmployeeController {
    @Autowired
    RemovedEmployeeDao removedEmployeeDao;
    @Autowired
    UpdateDao updateDao;
    @Autowired
    UserDao userDao;

    //離籍者全件取得
    @GetMapping("/emp/removed")
    public String showRemovedData(Model model) {
        Collection<Employee> removedData = removedEmployeeDao.selectRemovedDataAll();
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("removedData", removedData);
        return "removedEmployee";
    }

    //復帰機能
    @GetMapping("/emp/removed/{id}")
    public String userList(Model model, @PathVariable("id") Integer id) {
        PostEmployee emp = updateDao.selectEmpByID(id);
        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        emp.setUpdate_at(LocalDateTime.now().format(formatter));
        emp.setDelete_flag(0);
        updateDao.update(emp);

        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("noSelect", null);
        return "redirect:/emp";
    }
}
