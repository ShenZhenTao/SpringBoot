package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Video;
import com.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController//相当于restbody加controller
@RequestMapping
public class VideoController {
    @Autowired
    VideoService videoService;

//    搜索框功能
    @GetMapping("/sousuo")
    public List<Video> findVideoByName(@RequestParam(defaultValue = "") String title){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        if (!title.equals(""))
        queryWrapper.like("title",title);
        return videoService.list(queryWrapper);
    }

//    Detail页面展示内容
    @GetMapping("/detail")
    public Video findVideo(@RequestParam Integer id){
        return videoService.getById(id);
    }

//    热映查询
    @GetMapping("/reying")
    public List<Video> findVideos(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("popularity"); //查询结果倒叙
        queryWrapper.last("limit 3");   //将语句添加在sql语句的最后面
        return videoService.list(queryWrapper);
    }

//    国漫区首页展示
    @GetMapping("/guoman")
    public List<Video> findIndexByGuoMan(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("area","中国");
        queryWrapper.last("limit 12");
        return videoService.list(queryWrapper);
    }

    //    日漫区首页展示
    @GetMapping("/riman")
    public List<Video> findIndexByRiMan(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("area","日本");
        queryWrapper.last("limit 12");
        return videoService.list(queryWrapper);
    }

//    新片上线
    @GetMapping("/dongmanindex/newVideo")
    public List<Video> findNewVideo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String nowYear= sdf.format(date);
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("year",nowYear);
        queryWrapper.last("limit 12");
        return videoService.list(queryWrapper);
    }
//    排行榜
//    热映查询
    @GetMapping("/dongmanindex/PopularityList")
    public List<Video> findVideosByPopularity(){
        QueryWrapper queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("popularity"); //查询结果倒叙
        queryWrapper.last("limit 12");   //将语句添加在sql语句的最后面
        return videoService.list(queryWrapper);
    }


//    分页多条件查询
    @GetMapping("/dongmanindex/page")
    public IPage<Video> findVideo(@RequestParam Integer pageNum,
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
        return videoService.page(page,queryWrapper);
    }


}
