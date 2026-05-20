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
 * administratorsテーブルを操作するテーブル.
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
     * @param administrator 管理者ドメイン
     * @return 取得した管理者情報
     * */
    public Administrator login(Administrator administrator) {

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
        SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", administrator.getMailAddress()).addValue("password", administrator.getPassword());
        return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
    }

    /**
     * 新しい従業員情報を保存.
     * @param administrator 管理者ドメイン
     * @return 更新した件数
     * */
    public int save(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        String sql = """
                INSERT INTO
                    administrators(name, mail_address, password)
                VALUES(:name, :mailAddress, :password);
                """;
        return template.update(sql, param);
    }
}
