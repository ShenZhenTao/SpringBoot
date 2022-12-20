package com.example.service.upload;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.Video;
import org.springframework.web.multipart.MultipartFile;

public interface UploadVideo extends IService<Video> {
    void saveVideo(MultipartFile file,UploadVideo uploadVideo);
}
