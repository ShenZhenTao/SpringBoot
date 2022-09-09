package com.example.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.entity.Video;
import com.example.mapper.VideoDao;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

    public Result dowloadVideo(
            String title,
            String plot,
            String area,
            HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //        防止中文乱码
        String fileName= URLEncoder.encode("测试","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename*=UTF-8''"+fileName+".xlsx");

        ServletOutputStream outputStream=response.getOutputStream();
        ExcelWriterBuilder writerBuilder= EasyExcel.write(outputStream,Video.class);
        ExcelWriterSheetBuilder sheetBuilder=writerBuilder.sheet();

//        需要获得前端传回来的条件，通过该条件查询数据库再进行导出
        QueryWrapper queryWrapper=new QueryWrapper();
        if (!title.equals(""))
            queryWrapper.like("title",title);
        if (!plot.equals(""))
            queryWrapper.like("plot",plot);
        if (!area.equals(""))
            queryWrapper.like("area",area);
        List<Video> videos=videoDao.selectList(queryWrapper);
        sheetBuilder.doWrite(videos);
        return Result.success("导出成功");
    }


}
