package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Files;
import com.example.mapper.FilesDao;
import org.springframework.stereotype.Service;

@Service
public class FilesService extends ServiceImpl<FilesDao, Files> {
}
