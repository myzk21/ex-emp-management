package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
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
@RequestMapping("/loginForm")
public class AdministratorController {

    @Autowired
    private AdministratorService service;

    @GetMapping("")
    public String index(LoginForm loginForm) {
        return "administrator/login";
    }

    @PostMapping("/login")
    public String login(@Validated LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return index(loginForm);
        }
        //ログインチェックを行う
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(loginForm, administrator);
        Administrator loggedInAdministrator = service.login(administrator);

        if (loggedInAdministrator == null) {
            return "redirect:index";
            //エラーを表示する必要がある
        }
        return "redirect:/employeeList";
    }

}
