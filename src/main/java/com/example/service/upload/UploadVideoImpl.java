package com.example.service.upload;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Video;
import com.example.exception.VideoListener;
import com.example.mapper.VideoDao;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class UploadVideoImpl extends ServiceImpl<VideoDao,Video> implements UploadVideo{

    @Override
    public void saveVideo(MultipartFile file, UploadVideo uploadVideo) {
        try {
            //文件输入流
            InputStream in=file.getInputStream();
            //调用方法进行读取
            //吧service直接注入进来为了后面能使用

            //因为在listener中不能注入service所以在这个serviceiimpl中，通过listener使service注入进去，为了在listener中能够使用service中的发方法save/
            EasyExcel.read(in, Video.class,new VideoListener(uploadVideo)).sheet().doRead();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
