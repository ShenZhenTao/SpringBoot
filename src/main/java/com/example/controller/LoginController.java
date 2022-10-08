package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.controller.dto.UserDTO;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class LoginController {
    @Resource
    UserService userService;
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String account=userDTO.getAccount();
        String password=userDTO.getPassword();
//        if (username.length()==0 || password.length()==0)
        if(StringUtils.isBlank(account)||StringUtils.isBlank(password))
            return Result.error(Constants.CODE_400,"参数错误");
        UserDTO dto= userService.login(userDTO);
        return Result.success(dto,"登录成功");
//        设置token
        redisTemplate.opsForValue().set(username, dto.getToken(),3, TimeUnit.HOURS);
        return Result.success(dto);
    }



}
