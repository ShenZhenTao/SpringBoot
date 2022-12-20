package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;

//    @GetMapping("/list")
//    public TableDataInfo list(User user){
//        PageDomain pageDomain = TableSupport.buildPageRequest();
//        List<User> userList = userService.selectUserList(user);
//        Integer pageNum = pageDomain.getPageNum();
//        Integer pageSize = pageDomain.getPageSize();
//        List<User> data = ListPagHelper.paging(userList, pageNum, pageSize);
//        return getDataTableVo(data,data);
//    }
}
