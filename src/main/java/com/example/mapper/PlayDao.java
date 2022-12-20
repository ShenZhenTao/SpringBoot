package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Play;

import java.util.List;


public interface PlayDao extends BaseMapper<Play> {
    List<Play> findVidePlay(Integer id,Integer part);
    int del(Integer Playid);
    int delBatch(List<Integer> ids);
}
