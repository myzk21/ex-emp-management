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

/**
 * administratorsテーブルを操作するテーブル
 * */
@Repository
public class AdministratorRepository {


    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Administratorオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER =  new BeanPropertyRowMapper<>(Administrator.class);

    public Administrator login(Administrator administrator) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);

        String sql = """
                SELECT
                    id,
                    name,
                    mailAddress,
                    password
                FROM
                    administrators
                WHERE
                    id = :id;
                """;
        return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
    }
}
