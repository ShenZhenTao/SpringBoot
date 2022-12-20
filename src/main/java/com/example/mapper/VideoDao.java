package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Video;
import java.util.List;

//@Mapper
public interface VideoDao extends BaseMapper<Video>{

    public List<Video> findVideoByArea(String areaId);
    public List<Video> findNewVideo(String year);
}
