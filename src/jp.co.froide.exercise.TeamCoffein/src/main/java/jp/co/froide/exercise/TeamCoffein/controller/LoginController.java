package jp.co.froide.exercise.TeamCoffein.controller;

import jp.co.froide.exercise.TeamCoffein.dao.EmployeeDao;
import jp.co.froide.exercise.TeamCoffein.dao.UpdateDao;
import jp.co.froide.exercise.TeamCoffein.entity.PostEmployee;
import jp.co.froide.exercise.TeamCoffein.form.ConfMailForm;
import jp.co.froide.exercise.TeamCoffein.form.ConfPassForm;
import jp.co.froide.exercise.TeamCoffein.form.LoginForm;
import jp.co.froide.exercise.TeamCoffein.validation.MailFormValidator;
import jp.co.froide.exercise.TeamCoffein.validation.PassFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {

    @Autowired
    EmployeeDao empDao;
    @Autowired
    UpdateDao updateDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PassFormValidator passFormValidator;

    @Autowired
    MailFormValidator mailFormValidator;

    @InitBinder("confPassForm")
    public void validatorBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(passFormValidator);
    }

    @InitBinder("confMailForm")
    public void validatorBinder2(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(mailFormValidator);
    }

    @GetMapping("/emp/login")
    public String showLoginPage(@ModelAttribute LoginForm form, Model model) {
        if (!model.containsAttribute("message")) {
            model.addAttribute("message", null);
        }
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/emp/login")
    public String index(@Validated @ModelAttribute LoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }
        return "forward:" + "/processing";
    }

    @PostMapping("/success")
    public String loginSuccess(@ModelAttribute LoginForm form, RedirectAttributes ra) {
        ra.addFlashAttribute("message", "login_success");
        return "redirect:/emp";
    }

    @GetMapping("/loginFail")
    public String loginFailure(@ModelAttribute LoginForm form, Model model, RedirectAttributes ra) {
        ra.addFlashAttribute("message", "メールアドレスもしくはパスワードが違います。");
        return "redirect:/emp/login";
    }

    @GetMapping("/emp/forgetPass")
    public String forgetPass(Model model) {
        if (!model.containsAttribute("error")) {
            model.addAttribute("mailForm", new ConfMailForm());
        }
        return "PassConfMail";
    }

    @GetMapping("/emp/changePass")
    public String showPassForm(Model model) {
        if (model.containsAttribute("error")) {
            return "changePass";
        } else {
            return "redirect:/emp/login";
        }
    }

    @PostMapping("/emp/changePass")
    public String changePass(@Validated @ModelAttribute ConfMailForm form, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.mailForm", result);
            ra.addFlashAttribute("error", "this has errors");
            ra.addFlashAttribute("mailForm", form);
            return "redirect:/emp/forgetPass";
        }

        String email = form.getEmail();
        ConfPassForm passForm = new ConfPassForm();
        passForm.setEmail(email);
        model.addAttribute("email", passForm.getEmail());
        model.addAttribute("passForm", passForm);
        return "changePass";
    }

    @PostMapping("/emp/sucChange")
    public String successChangePass(@Validated @ModelAttribute ConfPassForm form, BindingResult result, Model model, RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.passForm", result);
            ra.addFlashAttribute("error", "this has errors");
            ra.addFlashAttribute("email", form.getEmail());
            ra.addFlashAttribute("passForm", form);
            return "redirect:/emp/changePass";
        }
        PostEmployee emp = empDao.selectByEmail(form.getEmail());
        String hashed_pass = passwordEncoder.encode(form.getPass());
        emp.setPassword(hashed_pass);
        updateDao.update(emp);
        return "redirect:/emp/login";
    }

    @RequestMapping(value = {"/emp/login", "/emp/changePass", "/emp/sucChange"}, params = "action=cancel")
    public String cancelButton() {
        return "redirect:/emp";
    }
}
