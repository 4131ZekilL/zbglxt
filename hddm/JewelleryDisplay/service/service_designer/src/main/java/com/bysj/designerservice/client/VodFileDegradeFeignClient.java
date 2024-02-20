package com.bysj.designerservice.client;

import com.bysj.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeVideo(String id) {
        return R.failed().message("删除视频出错了");
    }

    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.failed().message("删除多个视频出错了");
    }
}
