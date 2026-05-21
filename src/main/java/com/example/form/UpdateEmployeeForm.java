package com.example.form;

import jakarta.validation.constraints.NotBlank;

/**
 * 従業員更新時時に使用するフォーム.
 * */
public class UpdateEmployeeForm {
    /**扶養人数*/
    @NotBlank(message="扶養人数は必須です")
    private String dependentsCount;

    @NotBlank(message="特性は必須です")
    private String characteristics;

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(String dependentsCount) {
        this.dependentsCount = dependentsCount;
    }
}
