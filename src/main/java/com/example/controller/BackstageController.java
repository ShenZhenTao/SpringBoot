package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Play;
import com.example.entity.PlayManage;
import com.example.entity.User;
import com.example.entity.Video;
import com.example.service.PlayManageService;
import com.example.service.PlayService;
import com.example.service.UserService;
import com.example.service.VideoService;
import com.example.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public boolean Save(@RequestBody Video video){

        video.setPyKey(videoService.getPinYinHeadChar(video.getTitle()));
        System.out.println(video.getPyKey());
        return videoService.saveOrUpdate(video);
    }

    //    修改
    @PostMapping("/updatePlay")
    public boolean SavePlay(@RequestBody Play play){
        return playService.update(play, new QueryWrapper<Play>().eq("playId", play.getPlayId()));
    }

    //     删除单条记录
    @DeleteMapping("/delPlay/{playId}")
    public int deletePlay(@PathVariable Integer playId){
        return playService.deleteById(playId);
    }

    //批量删除
    @PostMapping("/delPlay/batch")
    public int deleteBatchPlay(@RequestBody List<Integer> ids){
        return playService.delBath(ids);
    }



    //     删除单条记录
    @DeleteMapping("/del/{videoId}")
    public boolean delete(@PathVariable Integer videoId){
        return videoService.removeById(videoId);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids){
        return videoService.removeByIds(ids);
    }

//  添加剧集
    @PostMapping("/savePlay")
    public boolean savePlay(@RequestBody Play play){
        return playService.save(play);
    }

//    分页查询剧集内容
    @GetMapping("/backstage/page")
    public List<PlayManage> findPlayPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String part,
                                         @RequestParam(defaultValue = "") String title
    ) {

        return playManageService.findPlayPage(part,title,pageNum,pageSize);
    }
//获取分页总条数
    @GetMapping("/backstage/count")
    public Integer selectCount(
                                @RequestParam(defaultValue = "") String part,
                                @RequestParam(defaultValue = "") String title){
        return playManageService.selectCount(part,title);
    }

    //    番剧分页多条件查询
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

        User user=userService.getById(Integer.valueOf(TokenUtils.getCurrentUser()));
        return videoService.page(page,queryWrapper);
    }

    @GetMapping("/userInfo")
    public Result userInfo(@RequestParam String account){
//        return userService.findUserInfo(account);
        return Result.success(userService.findUserInfo(account));
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


}
