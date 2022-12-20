package com.example.exception;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.domain.Video;
import com.example.service.upload.UploadVideo;

public class VideoListener extends AnalysisEventListener<Video> {

    private UploadVideo uploadVideo;

    public VideoListener(UploadVideo uploadVideo) {
        this.uploadVideo=uploadVideo;
    }

    public VideoListener(){

    }

    @Override
    public void invoke(Video video, AnalysisContext analysisContext) {

    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
