package com.example.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
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
    @ExcelIgnore
    @TableId
    private int videoId;//视频的id

    @ColumnWidth(20)
    @ExcelProperty("番剧标题")
    private String title;//视频标题

    @ColumnWidth(20)
    @ExcelProperty("视频简介")
    private String introduction;//视频简介

    @ExcelProperty("导演")
    private String director;//导演

    @ExcelIgnore
    private String imageUrl;//展示图

    @ExcelProperty("主演")
    private String actors;//主演

    @ExcelProperty("人气")
    private int popularity;//人气

    @ExcelProperty("剧情")
    private String plot;//剧情

    @ExcelProperty("地区")
    private String area;//地区

    @ExcelProperty("语言")
    private String language;//语言

    @ExcelProperty("年份")
    private String year;//年份

    @ExcelIgnore
    private String pyKey;//首字母缩写
}
