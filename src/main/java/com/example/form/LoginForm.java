package com.example.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * ログイン時に使用するフォーム
 * */
@Getter
@Setter
public class LoginForm {
    //** メールアドレス*/
    @NotBlank(message="メールアドレスは必須です")
    private String mailAddress;

    //** パスワード*/
    @NotBlank(message="パスワードは必須です")
    private String password;
}
