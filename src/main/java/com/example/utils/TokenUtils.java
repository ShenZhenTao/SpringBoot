package com.example.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtils {

//    @Autowired
//    public static UserService staticUserService;

//    @Resource
//    private UserService userService;
//
//    @PostConstruct
//    public void setStaticUserService(){
//
//        staticUserService=userService;
//    }

    /*
    * 生成token
    *
    * */
    public static String getToken(String userId,String sign){
       return JWT.create().withAudience(userId)   //将user id保存到token里面，作为载荷
        .withExpiresAt(DateUtil.offsetHour(new Date(),2))//2个小时后token过期
        .sign(Algorithm.HMAC256(sign));//以password作为token的密钥
    }

    /*
    * 通过token获取当前登录的用户信息
    * @return user对象
    * */

    public static Integer getCurrentUser(){
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token=request.getHeader("token");
        Integer userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        return userId;
//        if (token!="") {
//            try {
//                String userId = JWT.decode(token).getAudience().get(0);
//                System.out.println("用户的id是：============================="+Integer.valueOf(userId));
//                User user=staticUserService.getById(Integer.valueOf(userId));
//                System.out.println("user对象是：============================="+user);
//                return user;
//            }catch (Exception e){
//                return null;
//            }
//        }
//        return null;

    }
}
