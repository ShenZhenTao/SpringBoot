package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.domain.Video;
import com.example.service.impl.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController//相当于restbody加controller
@RequestMapping
public class VideoController {
    @Autowired
    VideoService videoService;

//    搜索框功能
    @GetMapping("/sousuo")
    public Result findVideoByName(@RequestParam(defaultValue = "") String title){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        if (!title.equals(""))
        queryWrapper.like("title",title);
        return Result.success(videoService.list(queryWrapper));
    }

//    Detail页面展示内容
    @GetMapping("/detail")
    public Result findVideo(@RequestParam Integer id){
        return Result.success(videoService.getById(id));
    }

//    热映查询
    @GetMapping("/reying")
    public Result findVideos(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("popularity"); //查询结果倒叙
        queryWrapper.last("limit 3");   //将语句添加在sql语句的最后面
        return Result.success(videoService.list(queryWrapper));
    }


//    首页展示
    @GetMapping("/display")
    public Result findDisPlay(@RequestParam String area){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("area",area);
        queryWrapper.last("limit 12");
        return Result.success(videoService.list(queryWrapper));
    }

//    新片上线
    @GetMapping("/dongmanindex/newVideo")
    public Result findNewVideo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String nowYear= sdf.format(date);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("year",nowYear);
        queryWrapper.last("limit 12");
        return Result.success(videoService.list(queryWrapper));
    }
//    排行榜
//    热映查询
    @GetMapping("/dongmanindex/PopularityList")
    public Result findVideosByPopularity(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("popularity"); //查询结果倒叙
        queryWrapper.last("limit 12");   //将语句添加在sql语句的最后面
        return Result.success(videoService.list(queryWrapper));
    }


//    分页多条件查询
    @GetMapping("/dongmanindex/page")
    public Result findVideo(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam(defaultValue = "") String plot,
                                  @RequestParam(defaultValue = "") String area,
                                  @RequestParam(defaultValue = "") String language,
                                  @RequestParam(defaultValue = "") String year,
                                  @RequestParam(defaultValue = "") String pyKey,
                                  @RequestParam(defaultValue = "") String title
                                  ) {
        IPage<Video> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Video> queryWrapper = new QueryWrapper<>();
        if (!plot.equals("")) {
            queryWrapper.like("plot", plot);
        }
        if (!area.equals("")) {
            queryWrapper.like("area", area);
        }
        if (!language.equals("")){
            queryWrapper.like("language", language);
        }
        if (!year.equals("")){
            queryWrapper.like("year",year);
        }
        if (!pyKey.equals("")){
            queryWrapper.like("pyKey",pyKey);
        }
        if (!title.equals("")){
            queryWrapper.like("title",title);
        }
        return Result.success(videoService.page(page,queryWrapper));
    }



}
