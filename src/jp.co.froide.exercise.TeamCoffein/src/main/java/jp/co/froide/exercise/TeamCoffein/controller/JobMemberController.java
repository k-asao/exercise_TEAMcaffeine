package jp.co.froide.exercise.TeamCoffein.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jp.co.froide.exercise.TeamCoffein.dao.UserDao;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.entity.Year;
import jp.co.froide.exercise.TeamCoffein.form.SearchForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.spring5.processor.SpringOptionInSelectFieldTagProcessor;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/")
public class JobMemberController {

    @Autowired
    UserDao userDao;

    @Autowired
    CsvViewController csvViewController;

    private final String limit = "20";

    Collection<Employee> searchList;

    @GetMapping("/")
    public String rootAddress(){ return "redirect:/emp";}





    @RequestMapping(value = {"/emp"}, method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String index(Model model, @RequestParam HashMap<String, String> params) {

        String currentPage = (params.get("page") == null) ? "1": params.get("page");
        String order = (params.get("order")==null) ? "asc" : params.get("order");
        String name = (params.get("name") == null) ? "" : params.get("name");
        Integer post_id = (params.get("post_id") == null) ? null : Integer.valueOf(params.get("post_id"));
        Integer dept_id = (params.get("dept_id") == null) ? null : Integer.valueOf(params.get("dept_id"));
        String hire_date = (params.get("hire_date") == null) ? "" : params.get("hire_date");


        int total = userDao.getSearch(order, name, post_id, dept_id, hire_date, 0);


        Integer totalPage = (total + Integer.parseInt(limit) - 1) / Integer.parseInt(limit);
        int page = Integer.parseInt(currentPage);
        int lim = Integer.parseInt(limit);
        Integer off = lim * (page - 1);


        SearchForm form = new SearchForm(order, name, post_id, dept_id, hire_date);
        System.out.println(form);
        Collection<Year> dateList = userDao.selectHireDateAll();
        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
        searchList = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim, off, 0);
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchForm_page", form);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", searchList);
        return "jobMemberForm";
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String search(@RequestParam HashMap<String, String> params, SearchForm form, Model model, RedirectAttributes ra) {
        String order = form.getOrder();
        String name = form.getName();
        Integer post_id = form.getPost_id();
        Integer dept_id = form.getDept_id();
        String hire_date = form.getHire_date();
        String currentPage = params.get("page");
        ra.addAttribute("name", name);
        String redirectUrl = (currentPage == null) ? "?order=" + order : "?page=" + currentPage + "&order=" + order;
        if(!name.equals("")) redirectUrl = redirectUrl + "&name={name}" ;
        if(post_id != null) redirectUrl = redirectUrl + "&post_id=" + post_id;
        if(dept_id != null) redirectUrl = redirectUrl + "&dept_id=" + dept_id;
        if(!hire_date.equals("")) redirectUrl = redirectUrl + "&hire_date=" + hire_date;
        System.out.println(redirectUrl);

        return "redirect:/emp" + redirectUrl;
    }

    @GetMapping(value = "**/employee.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public Object createCsv() throws JsonProcessingException {
        return csvViewController.csvCreator(searchList);
    }
}


