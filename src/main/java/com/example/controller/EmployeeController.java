package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 従業員関連機能の処理の制御を行うコントローラー
 * */
@Controller
@RequestMapping("/employeeTest")
public class EmployeeController {

    @GetMapping("")
    public String index() {
        return "employee/list";
    }
}
