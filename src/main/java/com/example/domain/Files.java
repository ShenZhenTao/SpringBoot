package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sysfile")
public class Files {
    @TableId
    private Integer avatarId;
    private String name;
    private String type;
    private Long size;
    private String url;
    private Boolean isDelete;
    private Boolean enable;
    private String md5;
}
