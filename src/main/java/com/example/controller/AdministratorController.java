package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 管理者関連の処理の制御を行うコントローラー
 * */
@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @Autowired
    private HttpSession session;

    @GetMapping("/loginForm")
    public String index(LoginForm loginForm) {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String login(@Validated LoginForm loginForm, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return index(loginForm);
        }
        //ログインチェックを行う
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(loginForm, administrator);
        try {
            Administrator loggedInAdministrator = service.login(administrator);
            session.setAttribute("administratorName", loggedInAdministrator.getName());

            return "redirect:/employee/showList";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("isLoginFailed", true);
            return "administrator/login";
        }
    }

    @GetMapping("/registerForm")
    public String registerForm(InsertAdministratorForm insertAdministratorForm) {
        return "administrator/insert";
    }

    @PostMapping("/save")
    public String administratorForm(@Validated InsertAdministratorForm insertAdministratorForm, BindingResult result) {
        if (result.hasErrors()) {
            return registerForm(insertAdministratorForm);
        }

        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(insertAdministratorForm, administrator);

        service.save(administrator);
        return "redirect:/administrator/loginForm";
    }


}
