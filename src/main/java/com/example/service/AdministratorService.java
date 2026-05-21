package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *  管理者情報の業務処理を行うサービスクラス.
 */
@Service
@Transactional
public class AdministratorService {

    /**管理者のリポジトリ*/
    @Autowired
    private AdministratorRepository repository;

    /**
     * ログイン.
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 取得した従業員情報
     * */
    public Administrator login(String mailAddress, String password) {
        return repository.findByMailAddressAndPassword(mailAddress, password);
    }

    /**
     * 従業員情報を登録.
     * @param administrator 従業員情報
     * */
    public void insert(Administrator administrator) {
        repository.insert(administrator);
    }

    /**
     * 従業員情報を更新する.
     *
     * @param administrator 管理者情報
     * */
    public void update(Administrator administrator) {
        repository.update(administrator);
    }

}