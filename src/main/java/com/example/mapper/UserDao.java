package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Play;
import com.example.entity.User;

public interface UserDao extends BaseMapper<User> {
    User findUserInfo(String account);
}
