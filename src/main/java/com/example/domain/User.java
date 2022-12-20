package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")
public class User {
    @TableId
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 管理员身份
     */
    private String status;

    /**
     * 用户头像外键
     */
    private Integer avatarId;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 居住地
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)//表示此属性不是数据库中的字段，但在项目中必须使用。
    private Files files;   //头像


}
