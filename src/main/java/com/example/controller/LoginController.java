package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.domain.dto.UserDTO;
import com.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/backstage")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;


    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        String account = userDTO.getAccount();
        String password = userDTO.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);
        //设置token
        redisTemplate.opsForValue().set(account, dto.getToken(), 3, TimeUnit.HOURS);
        return Result.success(dto, "登录成功");

    }
}
