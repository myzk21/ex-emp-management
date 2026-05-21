package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 *  従業員情報の業務処理を行うサービスクラス.
 */
@Service
public class EmployeeService {
    /**従業員情報を更新するリポジトリ*/
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 全ての従業員情報を取得.
     *
     * @return 全ての従業員情報
     * */
    public List<Employee> showEmployeeList() {
        return employeeRepository.findAll();
    }

    /**
     * 一件の従業員情報を取得.
     * @param id 検索に使用するemployeesの主キー
     * @return 取得した従業員情報
     * */
    public Employee showDetail(int id) {
        return employeeRepository.findById(id);
    }

    /**
     * 一件の従業員情報を更新.
     * @param id 検索に使用するemployeesの主キー
     * @param dependentsCount 更新する値（扶養人数）
     * */
    public void updateDependentsCount(int id, int dependentsCount, String characteristics) {
        employeeRepository.updateDependentsCountAndCharacteristics(id, dependentsCount, characteristics);
    }
}
