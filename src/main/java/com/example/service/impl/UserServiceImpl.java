package com.example.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.domain.User;
import com.example.domain.dto.UserDTO;
import com.example.exception.ServiceException;
import com.example.mapper.UserDao;
import com.example.service.UserService;
import com.example.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Resource
    private UserDao userDao;

    private static final Log LOG=Log.get();

    public UserDTO login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("account",userDTO.getAccount());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
            one=getOne(queryWrapper);//从数据库查询用户信息
        }catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        if (one !=null){
            BeanUtil.copyProperties(one,userDTO,true);
//            设置token
            String token= TokenUtils.getToken(one.getUserId().toString(),one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    public User findUserInfo(String account){
        return userDao.findUserInfo(account);
    }

    public Result updateUser(User user){
        User userDb=userDao.selectById(TokenUtils.getCurrentUser());
//        如果是超级管理员则可以修改所有用户的信息
        if (userDb.getStatus().equals("Administrator")){
            return Result.success(userDao.updateById(user) != 0,"修改成功");
        }else {
            //        通过token确定需要修改信息的账号
            Integer tokenUserId=userDb.getUserId();
            user.setUserId(tokenUserId);
            if (!user.getStatus().equals(userDb.getStatus()))
                throw new ServiceException(Constants.CODE_401,"用户权限不足无法修改");
            try {
                int number = userDao.updateById(user);
                return  Result.success(number!= 0,"修改成功");
            }catch (Exception e){
                return Result.error(Constants.CODE_400,"请输入正确的用户信息");
            }
        }
    }

    @Override
    public List<User> selectUserList(User user) {

        return null;
    }
}
