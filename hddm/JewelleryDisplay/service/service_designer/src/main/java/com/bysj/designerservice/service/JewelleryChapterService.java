package com.bysj.designerservice.service;

import com.bysj.designerservice.entity.JewelleryChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bysj.designerservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 珠宝 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
public interface JewelleryChapterService extends IService<JewelleryChapter> {

    List<ChapterVo> getChapterVideoByJewelleryId(String jewelleryId);

    boolean deleteChapter(String chapterId);
}
