package com.bysj.designerservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.designerservice.entity.JewelleryChapter;
import com.bysj.designerservice.entity.JewelleryVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 珠宝视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
public interface JewelleryVideoService extends IService<JewelleryVideo> {

    void removeVideoByCourseId(String jewelleryId);
}
