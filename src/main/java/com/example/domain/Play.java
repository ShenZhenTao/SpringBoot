package com.example.domain;

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
@TableName(value = "play")
public class Play {
    @TableId
    @ExcelIgnore
    private int playId;
    @ExcelIgnore
    private int videoId;
    @ColumnWidth(100)
    @ExcelProperty("剧集地址")
    private String url;
    @ExcelProperty("集数")
    private int part;
}
