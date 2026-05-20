package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 管理者関連の処理の制御を行うコントローラー
 * */
@Controller
@RequestMapping("/login")
public class AdministratorController {

    @GetMapping("")
    public String index() {
        return "/administrator/login";
    }

}
