package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.entity.Employee;
import jp.co.froide.exercise.TeamCoffein.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    EmployeeDao  empDao;

    @GetMapping("/emp/login")
    public String showLoginPage(@ModelAttribute LoginForm form, Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/emp/login")
    public String index(@Validated @ModelAttribute LoginForm form, BindingResult result){
        if(result.hasErrors()){
            System.out.println("BBBBBBBB");
            return "login";
        }
        System.out.println("aaaaaa");
        return "forward:" + "/processing" ;
    }

    @PostMapping("/success")
    public String loginSuccess(@ModelAttribute LoginForm form, RedirectAttributes ra){
        ra.addFlashAttribute("message", "login_success");
        return "redirect:/emp/create";
    }

    @GetMapping("/loginFail")
    public String loginFailure(@ModelAttribute LoginForm form, Model model){
        model.addAttribute("message", "login_failure");
        return "fail";
    }

}
