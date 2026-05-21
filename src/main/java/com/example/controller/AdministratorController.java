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
 * 管理者関連の処理の制御を行うコントローラー.
 * */
@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    /** 管理者用サービスクラス*/
    @Autowired
    private AdministratorService service;

    /** セッション*/
    @Autowired
    private HttpSession session;

    /**
     * ログイン画面を表示.
     *
     * @param loginForm ログイン用のフォームクラス
     * @return ログイン画面
     * */
    @GetMapping("/toLogin")
    public String toLogin(LoginForm loginForm) {
        return "administrator/login";
    }

    /**
     * ログインする.
     *
     * @param loginForm ログイン用のフォームクラス
     * @return ログインに成功時→ログイン画面　ログイン失敗時→従業員リスト画面
     * */
    @PostMapping("/login")
    public String login(@Validated LoginForm loginForm, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return toLogin(loginForm);
        }

        String mailAddress = loginForm.getMailAddress();
        String password = loginForm.getPassword();

        //ログインチェックを行う
        try {
            Administrator loggedInAdministrator = service.login(mailAddress, password);
            session.setAttribute("administratorName", loggedInAdministrator.getName());
            session.setAttribute("loggedInAdministrator", loggedInAdministrator);
            return "redirect:/employee/showList";
        } catch (Exception e) {
            model.addAttribute("isLoginFailed", true);
            return "administrator/login";
        }
    }

    /**
     * 従業員登録画面を表示.
     * @param insertAdministratorForm 従業位登録用のフォームクラス
     * @return 従業員登録画面
     * */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm) {
        return "administrator/insert";
    }

    /**
     * 従業員情報を保存.
     * @param insertAdministratorForm 従業位登録用のフォームクラス
     * @param result バリデーション後の値
     * @return ログインフォーム画面
     * */
    @PostMapping("/insert")
    public String insert(@Validated InsertAdministratorForm insertAdministratorForm, BindingResult result) {
        if (result.hasErrors()) {
            return toInsert(insertAdministratorForm);
        }

        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(insertAdministratorForm, administrator);

        service.insert(administrator);
        return "redirect:/administrator/toLogin";
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("loggedInAdministrator");
        return "redirect:/administrator/toLogin";
    }
}
