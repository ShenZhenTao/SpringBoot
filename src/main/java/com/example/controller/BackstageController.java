package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Play;
import com.example.entity.User;
import com.example.entity.Video;
import com.example.service.PlayManageService;
import com.example.service.PlayService;
import com.example.service.UserService;
import com.example.service.VideoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/backstage")
public class BackstageController {

    @Resource
    VideoService videoService;
    @Resource
    PlayService playService;
    @Resource
    PlayManageService playManageService;
    @Resource
    UserService userService;

//    保存
    @PostMapping("/save")
    public Result Save(@RequestBody Video video){

        video.setPyKey(videoService.getPinYinHeadChar(video.getTitle()));
        System.out.println(video.getPyKey());
        return Result.success(videoService.saveOrUpdate(video));
    }

    //    修改
    @PostMapping("/updatePlay")
    public Result SavePlay(@RequestBody Play play){
        return Result.success(playService.update(play, new QueryWrapper<Play>().eq("playId", play.getPlayId())));
    }

    //     删除单条记录
    @DeleteMapping("/delPlay/{playId}")
    public Result deletePlay(@PathVariable Integer playId){
        return Result.success(playService.deleteById(playId));
    }

    //批量删除
    @PostMapping("/delPlay/batch")
    public Result deleteBatchPlay(@RequestBody List<Integer> ids){
        return Result.success(playService.delBath(ids));
    }



    //     删除单条记录
    @DeleteMapping("/del/{videoId}")
    public Result delete(@PathVariable Integer videoId){
        return Result.success(videoService.removeById(videoId));
    }

    //批量删除
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(videoService.removeByIds(ids));
    }

//  添加剧集
    @PostMapping("/savePlay")
    public Result savePlay(@RequestBody Play play){
        return Result.success(playService.save(play));
    }

//    分页查询剧集内容
    @GetMapping("/backstage/page")
    public Result findPlayPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String part,
                                         @RequestParam(defaultValue = "") String title
    ) {

        return Result.success(playManageService.findPlayPage(part,title,pageNum,pageSize));
    }
//获取分页总条数
    @GetMapping("/backstage/count")
    public Result selectCount(
                                @RequestParam(defaultValue = "") String part,
                                @RequestParam(defaultValue = "") String title){
        return Result.success(playManageService.selectCount(part,title));
    }

    //    番剧分页多条件查询
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

    @GetMapping("/userInfo")
    public Result userInfo(@RequestParam String account){
        return Result.success(userService.findUserInfo(account));
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //    导出番剧信息
    @GetMapping("/test/downloadVideo")
    public Result downloadVideo(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String plot,
            @RequestParam(defaultValue = "") String area,
            HttpServletResponse response) throws IOException {

        videoService.dowloadVideo(title, plot, area, response);
        return null;
    }

//    导出剧集信息
    @GetMapping("/test/downloadPlay")
    public Result download(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") Integer part,
            HttpServletResponse response)throws IOException{

        playManageService.downloadPlay(title,part,response);
        return null;
    }

//    导入番剧信息
    public Result uploadVideo(MultipartFile file){

        return Result.success();
    }

}
