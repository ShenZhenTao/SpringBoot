package com.example.domain.dto;

import lombok.Data;
/*
* 接收前端登录请求的参数
* */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String account;
    private String token;
    private String status;
}
