package com.bysj.designerservice.client;

import com.bysj.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name="service-vod",fallback =  VodFileDegradeFeignClient.class)
public interface VodClient {
    //定义调用的方法路径
    @DeleteMapping("/jewelleryvod/video/removeVideo/{id}")
    public R removeVideo(@PathVariable String id);

    @DeleteMapping("/jewelleryvod/video/delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
