package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Play;
import com.example.mapper.PlayDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
