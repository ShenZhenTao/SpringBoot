package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.User;

import java.util.List;

public interface UserDao extends BaseMapper<User> {
    User findUserInfo(String account);
//    List<User> selectList(User user);


}
