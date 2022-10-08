package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.entity.dto.UserDTO;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class LoginController {
    @Autowired
    UserService userService;

    public class UserController {
        @Autowired
        private UserService userService;
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
}
