package jp.co.froide.exercise.TeamCoffein.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.froide.exercise.TeamCoffein.dao.UserDao;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.entity.Department;
import jp.co.froide.exercise.TeamCoffein.entity.Post;
import jp.co.froide.exercise.TeamCoffein.form.EmployeeForm;
import jp.co.froide.exercise.TeamCoffein.form.SearchForm;
//import jp.co.froide.exercise.TeamCoffein.validation.EmpFormValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class JobMemberController {

    @Autowired
    UserDao userDao;
    /*@Autowired
    EmpFormValidator empFormValidator;

    //html↓
    @InitBinder("employeeForm")
    public void validatorBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(empFormValidator);
    }*/


    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String search(@RequestParam HashMap<String, String> params, SearchForm form, Model model) {
        String order = form.getOrder();
        String name = form.getName();
        Integer post_id = form.getPost_id();
        Integer dept_id = form.getDept_id();
        String hire_date = form.getHire_date();
        String currentPage = params.get("page");

//        初期設定ではパラメータを取得できないので、1ページに設定
        if(currentPage == null) {
            currentPage = "1";
        }

//        データ取得時の取得件数、取得情報の指定
        HashMap<String, String> search = new HashMap<String, String>();
        search.put("limit", limit);
        search.put("page", currentPage);

        int total = 0;
        List<Employee> list = null;
        try {
//            データ総数を取得
            total = userDao.getSearch(order, name, post_id, dept_id, hire_date);
//            データ一覧を取得
//            list = userDao.getMemberList(search);

        } catch (Exception e) {
            return "error/fatal";
        }


//        ページネーション処理
//        "総数/1ページの表示数"から総ページ数を割り出す
        int totalPage = (total + Integer.valueOf(limit) -1) / Integer.valueOf(limit);
        int page = Integer.valueOf(currentPage);
        int lim = Integer.valueOf(limit);
        int off = lim * (page - 1);

        // 表示する最初のページ番号を算出（今回は3ページ表示する設定）
        // (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
//        int startPage = page - (page-1)%showPageSize;
        //表示する最後のページを算出
//        int endPage = (startPage + showPageSize-1 > totalPage) ? totalPage : startPage + showPageSize -1;

        Collection<Employee> searchList = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim,
                off);
        Collection<Employee> empDataList = userDao.selectEmpAll();
        Collection<Employee> dateList = userDao.selectEmpAll();

        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
//        model.addAttribute("emplo", list);
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);

        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", searchList);
        return "jobMemberForm";
    }

    //編集画面に飛ぶ
    @GetMapping("emp/edit{id}")
    public String touroku(@PathVariable("id")Integer id, Model model) {
        Employee empData = userDao.selectById(id);
        String name = empData.getName();
        model.addAttribute("name", name);
        model.addAttribute("searchForm", new SearchForm());
        return "jobMemberForm";
    }

    //1ページの表示数
    private final String limit = "20";

    //ページネーションで表示するページ数
//    private int showPageSize = 3;

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    @Transactional(readOnly = true)
    public String index(Model model, SearchForm form, @RequestParam HashMap<String, String> params) throws Exception {
//        パラメータを設定し、現在のページを取得する
        String currentPage = params.get("page");

//        初期設定ではパラメータを取得できないので、1ページに設定
        if(currentPage == null) {
            currentPage = "1";
        }

//        データ取得時の取得件数、取得情報の指定
        HashMap<String, String> search = new HashMap<String, String>();
        search.put("limit", limit);
        search.put("page", currentPage);

        int total = 0;
        List<Employee> list = null;
        try {
//            データ総数を取得
            total = userDao.getMemberListCount(); //データの数　
//            データ一覧を取得
//            list = userDao.getMemberList(search);

        } catch (Exception e) {
            return "error/fatal";
        }

//        ページネーション処理
//        "総数/1ページの表示数"から総ページ数を割り出す
        int totalPage = (total + Integer.valueOf(limit) -1) / Integer.valueOf(limit); //最大ページ数
        int page = Integer.valueOf(currentPage);
        int lim = Integer.valueOf(limit);
        int off = lim * (page - 1);

        // 表示する最初のページ番号を算出（今回は3ページ表示する設定）
        // (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
//        int startPage = page - (page-1)%showPageSize; 5
        //表示する最後のページを算出
//        int endPage = (startPage + showPageSize-1 > totalPage) ? totalPage : startPage + showPageSize -1;

//        最初のページはそのまま


//        最後のページ


        String order = "asc";
        String name = "";
        Integer post_id = null;
        Integer dept_id = null;
        String hire_date = "";
        Collection<Employee> dateList = userDao.selectEmpAll();
        Collection<Department> deptList = userDao.selectDeptAll();
        Collection<Post> postList = userDao.selectPostAll();
        Collection<Employee> searchList = userDao.selectSearchAll(order, name, post_id, dept_id, hire_date, lim,
                off);
        model.addAttribute("deptList", deptList);
        model.addAttribute("postList", postList);
        model.addAttribute("dateList", dateList);
        model.addAttribute("noSelect", null);

        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
        model.addAttribute("searchForm", form);
        model.addAttribute("searchList", searchList);
        return "jobMemberForm";
    }
}


//検索込みで表示非表示にできるようにする
//今検索前のボタン非表示はできている

//全部で何件あるか
//controller側で

//getは得たやつ
//新しいsearchFormを作った

