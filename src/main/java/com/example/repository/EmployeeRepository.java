package com.example.repository;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * employeesテーブルを操作するテーブル.
 * */
@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Administratorオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER =  new BeanPropertyRowMapper<>(Employee.class);

    /**
     * 全ての従業員情報を取得.
     *
     * @return 全ての従業員情報
     * */
    public List<Employee> findAll() {
        String sql = """
                SELECT
                    id,
                    name,
                    image,
                    gender,
                    hire_date,
                    mail_address,
                    zip_code,
                    address,
                    telephone,
                    salary,
                    characteristics,
                    dependents_count
                FROM
                    employees
                ORDER BY
                    name;
                """;
        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 一件の従業員情報を取得.
     *
     * @param id ID
     * @return 従業員情報
     * */
    public Employee findById(int id) {
        String sql = """
                SELECT
                    id,
                    name,
                    image,
                    gender,
                    hire_date,
                    mail_address,
                    zip_code,
                    address,
                    telephone,
                    salary,
                    characteristics,
                    dependents_count
                FROM
                    employees
                WHERE
                    id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 特定の従業員情報を更新.
     * @param id 検索に使用する主キーの値
     * @param dependentsCount 更新する値（扶養人数）
     * */
    public void updateDependentsCountAndCharacteristics(int id, int dependentsCount, String characteristics) {
        String sql = """
                UPDATE
                    employees
                SET
                    dependents_count = :dependentsCount,
                    characteristics = :characteristics
                WHERE
                    id = :id;
                """;
        SqlParameterSource param = new MapSqlParameterSource().addValue("dependentsCount", dependentsCount).addValue("characteristics", characteristics).addValue("id", id);
        template.update(sql, param);
    }
}
