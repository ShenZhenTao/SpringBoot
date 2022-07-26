package com.example.entity;

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
@TableName(value = "video")
public class Video {
    @TableId
    private int videoId;//视频的id
    private String title;//视频标题
    private String introduction;//视频简介
    private String director;//导演
    private String imageUrl;//展示图
    private String actors;//主演
    private int popularity;//人气
    private String plot;//剧情
    private String area;//地区
    private String language;//语言
    private String year;//年份
    private String pyKey;//首字母缩写
}
