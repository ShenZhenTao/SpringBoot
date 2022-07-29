package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.common.Result;
import com.example.entity.Play;
import com.example.service.PlayService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class PlayController {
    @Resource
    PlayService playService;


    @GetMapping("/play")
    public Result findVidePlay(@RequestParam Integer id){

        QueryWrapper<Play> queryWrapper=new QueryWrapper();
        queryWrapper.eq("videoId",id);
        queryWrapper.last("order by part");
        return Result.success(playService.list(queryWrapper));
    }

    @GetMapping("/playVideo")
    public Result findVideoPart(@RequestParam Integer id,
                                    @RequestParam Integer part){
        QueryWrapper<Play> queryWrapper=new QueryWrapper();
        queryWrapper.eq("videoId",id);
        queryWrapper.eq("part",part);
        return Result.success(playService.list(queryWrapper));
    }

//    @GetMapping(value = "/playVideo",produces = "video/mp4")
//    public ResponseEntity<FileSystemResource> findpart(
//                                                        @RequestParam Integer videoId,
//                                                        @RequestParam Integer partId
//                                                        ) throws FileNotFoundException {
//        String vid= String.valueOf(videoId);
//        String pid= String.valueOf(partId);
//        File file=new File("..\\Video\\"+vid+"\\"+pid+".mp4");
//        return ResponseEntity.ok(new FileSystemResource(file));
//    }
}
