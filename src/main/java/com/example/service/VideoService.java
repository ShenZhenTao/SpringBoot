package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Video;
import com.example.mapper.VideoDao;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VideoService extends ServiceImpl<VideoDao, Video> {

    @Resource
    private VideoDao videoDao;
    public List<Video> findVideoByArea(String areaName){
        List<Video> list=videoDao.findVideoByArea(areaName);
        return list;
    }

    public List<Video> findNewVideo(String year){
        List<Video> list=videoDao.findNewVideo(year);
        return list;
    }

    public String getPinYinHeadChar(String str) {
        String convert = "";
        if (str== null || str.length()==0) {
            return convert;
        }
        char word = str.charAt(0);
        // 提取汉字的首字母
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        convert += pinyinArray[0].charAt(0);

        return convert.toUpperCase();
    }
}
