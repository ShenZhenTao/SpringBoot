package com.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.domain.User;
import com.example.mapper.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Resource
    private UserDao userDao;
    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("account","admin");
        wrapper.eq("password","123")
        List<User> userList = userDao.selectList(wrapper);
        System.out.println(userList);
    }

}
