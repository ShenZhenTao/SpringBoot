package com.example.service;

import com.example.domain.User;

import java.util.List;

public interface UserService {
    /**
     * 显示用户列表
     * @param user
     * @return
     */
    List<User> selectUserList(User user);
}
