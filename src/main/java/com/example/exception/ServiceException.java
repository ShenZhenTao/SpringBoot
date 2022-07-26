package com.example.exception;
import lombok.Getter;

/*
* 自定义异常，继承运行时异常
* */
public class ServiceException extends RuntimeException{
    @Getter
    private String code;
    public ServiceException(String code, String msg) {
        super(msg);
        this.code=code;
    }
}
