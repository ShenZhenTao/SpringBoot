package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.controller.dto.UserDTO;
import com.example.service.UserService;
import com.example.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@RequestMapping
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
//        if (username.length()==0 || password.length()==0)
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password))
            return Result.error(Constants.CODE_400,"参数错误");
        UserDTO dto= userService.login(userDTO);
        return Result.success(dto);
    }
}
