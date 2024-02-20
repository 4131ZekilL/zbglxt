package com.bysj.designerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.designerservice.client.VodClient;
import com.bysj.designerservice.entity.JewelleryVideo;
import com.bysj.designerservice.mapper.JewelleryVideoMapper;
import com.bysj.designerservice.service.JewelleryVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 珠宝视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@Service
public class JewelleryVideoServiceImpl extends ServiceImpl<JewelleryVideoMapper, JewelleryVideo> implements JewelleryVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String jewelleryId) {
        QueryWrapper<JewelleryVideo> wrapperVideo=new QueryWrapper<>();
        wrapperVideo.eq("jewellery_id",jewelleryId);
        wrapperVideo.select("video_source_id");
        List<JewelleryVideo> jewelleryVideoList = baseMapper.selectList(wrapperVideo);

        List <String> videoIds = new ArrayList<>();
        for (int i = 0; i < jewelleryVideoList.size(); i++) {
            JewelleryVideo jewelleryVideo = jewelleryVideoList.get(i);
            String videoSourceId = jewelleryVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }

        }
        if (videoIds.size()>0){
            vodClient.removeVideoList(videoIds);
        }
        QueryWrapper<JewelleryVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("jewellery_id",jewelleryId);
        baseMapper.delete(wrapper);

    }
}
