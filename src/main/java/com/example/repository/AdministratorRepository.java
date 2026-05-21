package com.example.repository;

import com.example.domain.Administrator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * administratorsテーブルを操作するリポジトリ.
 * */
@Repository
public class AdministratorRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Administratorオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER =  new BeanPropertyRowMapper<>(Administrator.class);

    /**
     * ログイン.
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 取得した管理者情報
     * */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password) {

        String sql = """
                SELECT
                    id,
                    name,
                    mail_address,
                    password
                FROM
                    administrators
                WHERE
                    mail_address = :mailAddress
                AND
                    password = :password;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
        return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
    }

    /**
     * 従業員情報を保存する.
     *
     * @param administrator 管理者情報
     * */
    public void insert(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String sql = """
                INSERT INTO
                    administrators(name, mail_address, password)
                VALUES(:name, :mailAddress, :password);
                """;
        template.update(sql, param);
    }
}
