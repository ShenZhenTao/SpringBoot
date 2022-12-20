package com.example.enums;

public enum StatusEnum {
    正常(0),
    停用(1);
    private Integer value;

    StatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
