package com.bysj.designerservice.controller;


import com.bysj.commonutils.R;
import com.bysj.designerservice.client.VodClient;
import com.bysj.designerservice.entity.JewelleryVideo;
import com.bysj.designerservice.service.JewelleryVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 珠宝视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@RestController
@CrossOrigin
@RequestMapping("/jewelleryservice/video")
public class JewelleryVideoController {

    @Autowired
    private VodClient vodClient;
    @Autowired
    private JewelleryVideoService videoService;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody JewelleryVideo jewelleryVideo){
        videoService.save(jewelleryVideo);
        return R.succeed();
    }

    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        JewelleryVideo jewelleryVideo = videoService.getById(id);
        String videoSourceId = jewelleryVideo.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.removeVideo(videoSourceId);
        }
        videoService.removeById(id);
        return R.succeed();
    }
}

