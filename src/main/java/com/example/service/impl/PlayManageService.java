package com.example.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.domain.PlayManage;
import com.example.mapper.PlayManageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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

    public Result downloadPlay(String title, Integer part, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        //        防止中文乱码
        String fileName= URLEncoder.encode("测试","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename*=UTF-8''"+fileName+".xlsx");

        ServletOutputStream outputStream=response.getOutputStream();
        ExcelWriterBuilder writerBuilder= EasyExcel.write(outputStream, PlayManage.class);
        ExcelWriterSheetBuilder sheetBuilder=writerBuilder.sheet();

//        需要获得前端传回来的条件，通过该条件查询数据库再进行导出
        List<PlayManage> play=playManageDao.downloadPlay(title,part);
        sheetBuilder.doWrite(play);
        return Result.success("导出成功");
    }
}
