package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param administrator 従業員情報
     * @return 取得した従業員情報
     * */
    public Administrator login(Administrator administrator) {
        return repository.login(administrator);
    }

    /**
     * 従業員情報を登録.
     * @param administrator 従業員情報
     * */
    public void save(Administrator administrator) {
        repository.save(administrator);
    }

}