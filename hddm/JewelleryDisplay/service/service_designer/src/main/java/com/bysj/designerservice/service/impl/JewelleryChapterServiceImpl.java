package com.bysj.designerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.designerservice.entity.JewelleryChapter;
import com.bysj.designerservice.entity.JewelleryVideo;
import com.bysj.designerservice.entity.chapter.ChapterVo;
import com.bysj.designerservice.entity.chapter.VideoVo;
import com.bysj.designerservice.mapper.JewelleryChapterMapper;
import com.bysj.designerservice.service.JewelleryChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.designerservice.service.JewelleryVideoService;
import com.bysj.servicebase.exceptionHandler.ZDYException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 珠宝 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@Service
public class JewelleryChapterServiceImpl extends ServiceImpl<JewelleryChapterMapper, JewelleryChapter> implements JewelleryChapterService {

    @Autowired
    private JewelleryVideoService jewelleryVideoService;
    @Override
    public List<ChapterVo> getChapterVideoByJewelleryId(String jewelleryId) {
        //根据id查询珠宝的大纲
        QueryWrapper<JewelleryChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("jewellery_id",jewelleryId);
        List<JewelleryChapter> jewelleryChapterList = baseMapper.selectList(wrapperChapter);
        //根据id查询珠宝的大纲里面的视频
        QueryWrapper<JewelleryVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("jewellery_id",jewelleryId);
        List<JewelleryVideo> jewelleryVideoList = jewelleryVideoService.list(wrapperVideo);

        //创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        for (int i = 0; i < jewelleryChapterList.size(); i++) {
            JewelleryChapter jewelleryChapter = jewelleryChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(jewelleryChapter,chapterVo);

            finalList.add(chapterVo);

            List<VideoVo> VideoList = new ArrayList<>();

            for (int j = 0; j < jewelleryVideoList.size(); j++) {
                JewelleryVideo jewelleryVideo = jewelleryVideoList.get(j);
                if (jewelleryVideo.getChapterId().equals(jewelleryChapter.getId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(jewelleryVideo,videoVo);
                    VideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(VideoList);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<JewelleryVideo>wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = jewelleryVideoService.count(wrapper);
        if (count>0){
            throw new ZDYException(10001,"不能删除");
        }else {
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }
    }
}
