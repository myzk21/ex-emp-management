package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 従業員更新時時に使用するフォーム
 * */
public class UpdateEmployeeForm {
    @NotBlank(message="不要人数は必須です")
    private String dependentsCount;

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
}
