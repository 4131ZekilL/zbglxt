package com.bysj.vod.controller;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.bysj.commonutils.R;
import com.bysj.servicebase.exceptionHandler.ZDYException;
import com.bysj.vod.service.VodService;
import com.bysj.vod.utils.ConstantPropertiesUtil;
import com.bysj.vod.utils.InitVod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/jewelleryvod/video")
public class VodController {

    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file){
       String videoId =  vodService.uploadAlyVideo(file);
        return R.succeed().data("videoId",videoId);
    }
    @DeleteMapping("removeVideo/{id}")
    public R removeVideo(@PathVariable String id){
        try{
            DefaultAcsClient client = InitVod.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.succeed();
        }catch (Exception e){
            throw  new ZDYException(10001,"删除视频失败");
        }

    }
    @DeleteMapping("delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List videoIdList){
        vodService.removeMoreVideo(videoIdList);
        return R.succeed();
    }
    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth (@PathVariable String id){
        try {
            DefaultAcsClient client = InitVod.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);

            GetVideoPlayAuthRequest  request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);

            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.succeed().data("playAuth",playAuth);
        }catch (Exception e){
            throw new ZDYException(10001,"获取凭证失败");
        }
    }
}
