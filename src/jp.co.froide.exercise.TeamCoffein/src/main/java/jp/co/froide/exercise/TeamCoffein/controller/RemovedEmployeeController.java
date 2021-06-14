package jp.co.froide.exercise.TeamCoffein.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.dao.UserDao;
import jp.co.froide.exercise.TeamCoffein.entity.*;
import jp.co.froide.exercise.TeamCoffein.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Controller
public class RemovedEmployeeController {
    @Autowired
    UserDao userDao;
    @Autowired
    UpdateDao updateDao;
    @Autowired
    CsvViewController csvViewController;

    private final String limit = "20";
    Collection<Employee> removedData;

    @GetMapping("/emp/error")
    public String error(){return "dberror";}

    @GetMapping("/emp/removed/{id}")
    public String recoveryRemovedData(Model model, @PathVariable("id") Integer id) {
        PostEmployee emp;
        try {
            emp = updateDao.selectEmpByID(id);
        }catch (Exception e){
            return "dberror";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        emp.setUpdate_at(LocalDateTime.now().format(formatter));
        emp.setDelete_flag(0);
        try {
            updateDao.update(emp);
        }catch (Exception e){
            return "dberror";
        }
        return "redirect:/emp/removed";
    }

    @GetMapping(value = "**/removed.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public Object createCsv() throws JsonProcessingException {
        return csvViewController.csvCreator(removedData);
    }

    @RequestMapping(value = "/emp/removed", method = RequestMethod.POST)
    public String search(@RequestParam HashMap<String, String> params, SearchForm form, Model model, RedirectAttributes ra) {
        String order = form.getOrder();
        String name = form.getName();
        Integer post_id = form.getPost_id();
        Integer dept_id = form.getDept_id();
        String hire_date = form.getHire_date();
        String currentPage = params.get("page");


        String redirectUrl = (currentPage == null) ? "?order=" + order : "?page=" + currentPage + "&order=" + order;
        if(!name.equals("")) {
            ra.addAttribute("name", name);
            redirectUrl = redirectUrl + "&name={name}" ;
        }
        if(post_id != null) redirectUrl = redirectUrl + "&post_id=" + post_id;
        if(dept_id != null) redirectUrl = redirectUrl + "&dept_id=" + dept_id;
        if(!hire_date.equals("")) redirectUrl = redirectUrl + "&hire_date=" + hire_date;

        return "redirect:/emp/removed" + redirectUrl;
    }

    @RequestMapping(value = "/emp/removed", method = RequestMethod.GET)
    public String showRemovedData(Model model, @RequestParam HashMap<String, String> params) throws Exception {
        String currentPage = (params.get("page") == null) ? "1": params.get("page");
        String order = params.get("order");
        String name = (params.get("name") == null) ? "" : params.get("name");
        Integer post_id = (params.get("post_id") == null) ? null : Integer.valueOf(params.get("post_id"));
        Integer dept_id = (params.get("dept_id") == null) ? null : Integer.valueOf(params.get("dept_id"));
        String hire_date = (params.get("hire_date") == null) ? "" : params.get("hire_date");


        int total;
        try {
            total = userDao.getSearch(order, name, post_id, dept_id, hire_date, 1);
        }catch (Exception e){
            return "dberror";
        }


        Integer totalPage = (total + Integer.parseInt(limit) - 1) / Integer.parseInt(limit);
        int page = Integer.parseInt(currentPage);
        int lim = Integer.parseInt(limit);
        Integer off = lim * (page - 1);


        SearchForm form = new SearchForm(order, name, post_id, dept_id, hire_date);
        System.out.println(form);
        Collection<Year> dateList;
        Collection<Department> deptList;
        Collection<Post> postList;
        try {
            dateList = userDao.selectHireDateAll();
            deptList = userDao.selectDeptAll();
            postList = userDao.selectPostAll();
            removedData = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim, off, 1);
        }catch (Exception e){
            return "dberror";
        }
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchForm_page", form);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", removedData);
        return "removedEmployee";
    }

}
