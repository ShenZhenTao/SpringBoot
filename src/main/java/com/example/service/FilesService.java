package com.example.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Files;
import com.example.mapper.FilesDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Service
public class FilesService extends ServiceImpl<FilesDao, Files> {
    @Resource FilesDao filesDao;

    @Value("${files.upload.path}")
    private String fileUploadPath;

//    public String upload(MultipartFile file) throws IOException {
//        String originalFileName=file.getOriginalFilename();
//        String type= FileUtil.extName(originalFileName);
//        long size=file.getSize();
////        先存储到磁盘
//        File uploadParentFile=new File(fileUploadPath);
////        判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
//        if(!uploadParentFile.exists()){
//            uploadParentFile.mkdir();
//        }
////        定义一个文件唯一的标识码
//        String uid= IdUtil.fastSimpleUUID();
//        String fileUid=uid+ StrUtil.DOT+type;
//        File uploadFile=new File(fileUploadPath+fileUid);
////        获取文件的md5
//        String md5= SecureUtil.md5(file.getInputStream());
////        查询文件的md5
//        Files files=getFileByMd5(md5);
//        String url;
//        if (files!=null){
//            url=files.getUrl();
//        }else {
//            file.transferTo(uploadFile);
//            url="http://localhost:9091/file/"+fileUid;
//            //       把获取到的文件存储到磁盘目录
//            file.transferTo(uploadFile);
//        }
//
////        存储数据库
//        Files savefiles=new Files();
//        savefiles.setName(originalFileName);
//        savefiles.setType(type);
//        savefiles.setSize(size/1024);
//        savefiles.setUrl(url);
//        savefiles.setMd5(md5);
//        filesDao.insert(savefiles);
//
//        return url;
//    }

    public String upload(MultipartFile file) throws IOException {
        String originalFileName=file.getOriginalFilename();
        String type= FileUtil.extName(originalFileName);
        long size=file.getSize();
//        先存储到磁盘
        File uploadParentFile=new File(fileUploadPath);
//        判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdir();
        }
//        定义一个文件唯一的标识码
        String uid= IdUtil.fastSimpleUUID();
        String fileUid=uid+ StrUtil.DOT+type;
        File uploadFile=new File(fileUploadPath+fileUid);
//        获取文件的md5
        String md5= SecureUtil.md5(file.getInputStream());
//        查询文件的md5
        Files files=getFileByMd5(md5);
        String url;
        if (files!=null){
            url=files.getUrl();
        }else {
            file.transferTo(uploadFile);
            url="http://localhost:9091/file/"+fileUid;
            //       把获取到的文件存储到磁盘目录
            file.transferTo(uploadFile);
        }

//        存储数据库
        Files savefiles=new Files();
        savefiles.setName(originalFileName);
        savefiles.setType(type);
        savefiles.setSize(size/1024);
        savefiles.setUrl(url);
        savefiles.setMd5(md5);
        filesDao.insert(savefiles);

        return url;
    }


    public void download(String fileUid, HttpServletResponse response) throws IOException {
        //        根据文件的唯一表示码获取文件
        File uploadFile=new File(fileUploadPath+fileUid);
//        设置输出流的格式
        ServletOutputStream os= response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;filename"+ URLEncoder.encode(fileUid,"UTF-8"));
        response.setContentType("application/octet-stream");
//        读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }



    private Files getFileByMd5(String md5){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("md5",md5);
        List<Files> filesList=filesDao.selectList(queryWrapper);
        return filesList.size()==0 ? null : filesList.get(0);
    }
}
