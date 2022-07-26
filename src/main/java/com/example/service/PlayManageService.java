package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Play;
import com.example.entity.PlayManage;
import com.example.mapper.PlayDao;
import com.example.mapper.PlayManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlayManageService extends ServiceImpl<PlayManageDao, PlayManage> {
    @Resource
    PlayManageDao playManageDao;
    public List<PlayManage> findPlayPage(String part, String title, int pageNum, int pageSize){
        int start=(pageNum-1)*pageSize;
        return playManageDao.findPlayPage(part,title,start,pageSize);
    }

    public Integer selectCount(String part, String title){
        return playManageDao.selectCount(part,title);
    }
}
