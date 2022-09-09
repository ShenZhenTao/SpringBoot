package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName(value = "user")
public class User {
    @TableId
    private Integer userId;
    private String username;
    private String account;  //登录账号
    private String password;
    private String status;  //管理员身份
    private Integer avatarId; //用户头像外键
    private String telephone;//电话号码
    private String address; //居住地
    private String contactAddress;//联系地址

    @TableField(exist = false)//表示此属性不是数据库中的字段，但在项目中必须使用。
    private Files files;   //头像


}
