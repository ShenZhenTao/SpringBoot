package com.example.controller;

import com.example.service.impl.FilesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FilesService filesService;

//    @PostMapping("/upload")
//    public String upload(@RequestParam MultipartFile file)throws IOException{
//        return filesService.upload(file);
//    }

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file)throws IOException{
        return filesService.upload(file);
    }


    @GetMapping("{fileUid}")
    public void download(@PathVariable String fileUid, HttpServletResponse response)throws IOException{
        filesService.download(fileUid,response);
    }
}
