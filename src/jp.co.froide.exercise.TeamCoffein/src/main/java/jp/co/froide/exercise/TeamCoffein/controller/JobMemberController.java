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


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String search(@RequestParam HashMap<String, String> params, SearchForm form, Model model) {
        String order = form.getOrder();
        String name = form.getName();
        Integer post_id = form.getPost_id();
        Integer dept_id = form.getDept_id();
        String hire_date = form.getHire_date();
        String currentPage = params.get("page");

        if (currentPage == null) {
            currentPage = "1";
        }

        HashMap<String, String> search = new HashMap<String, String>();
        search.put("limit", limit);
        search.put("page", currentPage);

        int total = 0;
        try {
            total = userDao.getSearch(order, name, post_id, dept_id, hire_date, 0);
        } catch (Exception e) {
            return "error/fatal";
        }

        int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
        int page = Integer.valueOf(currentPage);
        int lim = Integer.valueOf(limit);
        int off = lim * (page - 1);


        searchList = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim, off, 0);
        Collection<Year> dateList = userDao.selectHireDateAll();

        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", searchList);
        return "jobMemberForm";
    }


    @RequestMapping(value = {"/emp","/"}, method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String index(Model model, SearchForm form, @RequestParam HashMap<String, String> params) throws Exception {
        String currentPage = params.get("page");

        if (currentPage == null) {
            currentPage = "1";
        }

        HashMap<String, String> search = new HashMap<String, String>();
        search.put("limit", limit);
        search.put("page", currentPage);

        int total = 0;
        try {
            total = userDao.getMemberListCount();
        } catch (Exception e) {
            return "error/fatal";
        }

        int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
        int page = Integer.valueOf(currentPage);
        int lim = Integer.valueOf(limit);
        int off = lim * (page - 1);


        String order = "asc";
        String name = "";
        Integer post_id = null;
        Integer dept_id = null;
        String hire_date = "";
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
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", searchList);
        return "jobMemberForm";
    }

    @GetMapping(value = "**/employee.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public Object createCsv() throws JsonProcessingException {
        return csvViewController.csvCreator(searchList);
    }
}


