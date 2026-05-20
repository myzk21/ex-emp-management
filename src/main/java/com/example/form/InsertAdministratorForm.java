package com.example.form;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 管理者登録時に使用するフォーム
 * */
//@Getter
//@Setter
public class InsertAdministratorForm {
    @NotBlank(message="名前は必須です")
    private String name;

    @NotBlank(message="メールアドレス")
    private String mailAddress;

    @NotBlank(message="パスワード")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
