package com.example.controller;

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
 * 従業員関連機能の処理の制御を行うコントローラー
 * */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/showList")
    public String index(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    @GetMapping("/showDetail")
    public String showDetail(int id, Model model, UpdateEmployeeForm updateEmployeeForm) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "employee/detail";
    }

    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm updateEmployeeForm, BindingResult result, int id, Model model) {
        if (result.hasErrors()) {
            return showDetail(id, model, updateEmployeeForm);
        }

        employeeService.update(id, Integer.parseInt(updateEmployeeForm.getDependentsCount()));
        return "redirect:/employee/showList";
    }
}
