package com.example.controller;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員関連機能の処理の制御を行うコントローラー.
 * */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    /** 従業員に関する業務処理を行うサービス*/
    @Autowired
    private EmployeeService employeeService;

    /**セッション*/
    @Autowired
    private HttpSession session;

    /**
     * 従業員リストを表示.
     * @param model リクエストスコープ
     * @return 従業員リスト画面
     * */
    @GetMapping("/showList")
    public String showEmployeeList(Model model) {
        if (session.getAttribute("loggedInAdministrator") == null) {
            return "redirect:/administrator/toLogin";
        }
        List<Employee> employeeList = employeeService.showEmployeeList();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    /**
     * 従業員詳細情報を表示.
     * @param id employeesテーブルの主キー
     * @param model リクエストスコープ
     * @param updateEmployeeForm 更新フォームの値
     * @return 従業員詳細画面
     * */
    @GetMapping("/showDetail")
    public String showDetail(int id, Model model, UpdateEmployeeForm updateEmployeeForm) {
        Employee employee = employeeService.showDetail(id);
        model.addAttribute("employee", employee);
        return "employee/detail";
    }

    /**
     * 従業員詳細情報を更新.
     * @param updateEmployeeForm 更新フォームの値
     * @param result バリデーション後の従業員情報
     * @param id employeesテーブルの主キー
     * @param model リクエストスコープ
     * */
    @PostMapping("/updateDependentsCount")
    public String updateDependentsCount(@Validated UpdateEmployeeForm updateEmployeeForm, BindingResult result, int id, Model model) {
        if (result.hasErrors()) {
            return showDetail(id, model, updateEmployeeForm);
        }

        employeeService.updateDependentsCount(id, Integer.parseInt(updateEmployeeForm.getDependentsCount()), updateEmployeeForm.getCharacteristics());
        return "redirect:/employee/showList";
    }
}
