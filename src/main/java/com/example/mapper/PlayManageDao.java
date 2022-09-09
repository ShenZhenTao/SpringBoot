package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Play;
import com.example.entity.PlayManage;

import java.util.List;

public interface PlayManageDao extends BaseMapper<PlayManage> {
    public List<PlayManage> findPlayPage(String part, String title, int start, int pageSize);
    public Integer selectCount(String part, String title);
    public List<PlayManage> downloadPlay(String title,Integer part);
}
