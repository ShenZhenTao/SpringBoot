package com.example.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.entity.Play;
import com.example.entity.PlayManage;
import com.example.entity.Video;
import com.example.mapper.PlayDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class PlayService extends ServiceImpl<PlayDao, Play> {

    @Resource
    PlayDao playDao;
    public List<Play> findVideoPlay(Integer id,Integer part){
        return playDao.findVidePlay(id,part);
    }


    public int deleteById(Integer id){
        return playDao.del(id);
    }

    public int delBath(List<Integer> ids){
        return playDao.delBatch(ids);
    }


}
