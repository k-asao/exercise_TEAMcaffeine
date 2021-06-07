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

    @GetMapping("/emp/removed/{id}")
    public String recoveryRemovedData(Model model, @PathVariable("id") Integer id) {
        PostEmployee emp = updateDao.selectEmpByID(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        emp.setUpdate_at(LocalDateTime.now().format(formatter));
        emp.setDelete_flag(0);
        updateDao.update(emp);
        return "redirect:/emp";
    }

    @GetMapping(value = "**/removed.csv", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE + "; charset=UTF-8; Content-Disposition: attachment")
    @ResponseBody
    public Object createCsv() throws JsonProcessingException {
        return csvViewController.csvCreator(removedData);
    }

    @RequestMapping(value = "/emp/removed", method = RequestMethod.POST)
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
            total = userDao.getSearch(order, name, post_id, dept_id, hire_date,1);
        } catch (Exception e) {
            return "error/fatal";
        }

        int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
        int page = Integer.valueOf(currentPage);
        int lim = Integer.valueOf(limit);
        int off = lim * (page - 1);

        removedData = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim, off, 1);
        Collection<Year> dateList = userDao.selectHireDateAll();
        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", removedData);
        return "removedEmployee";
    }

    @RequestMapping(value = "/emp/removed", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String showRemovedData(Model model, SearchForm form, @RequestParam HashMap<String, String> params) throws Exception {
        String currentPage = params.get("page");

        if (currentPage == null) {
            currentPage = "1";
        }

        HashMap<String, String> search = new HashMap<String, String>();
        search.put("limit", limit);
        search.put("page", currentPage);

        int total = 0;
        try {
            total = userDao.getRemovedMemberListCount();
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
        removedData = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim, off, 1);
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", removedData);
        return "removedEmployee";
    }

}
