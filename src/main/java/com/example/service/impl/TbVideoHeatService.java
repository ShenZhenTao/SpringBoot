package com.example.service.impl;

import com.example.mapper.TbVideoHeatDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TbVideoHeatService {
    @Resource
    private TbVideoHeatDao tbVideoHeatDao;

    /**
     * 统计视频热度，存入redis
     */
    public Integer addTbVideoHeatService(Double duration, Double currentTime) {
        Double heat = duration / currentTime;
        return null;
    }
}
