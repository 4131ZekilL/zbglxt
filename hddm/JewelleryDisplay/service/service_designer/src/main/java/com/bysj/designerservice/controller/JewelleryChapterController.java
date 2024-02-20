package com.bysj.designerservice.controller;


import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.JewelleryChapter;
import com.bysj.designerservice.entity.chapter.ChapterVo;
import com.bysj.designerservice.service.JewelleryChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 珠宝 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@RestController
@RequestMapping("/jewelleryservice/chapter")
@CrossOrigin
public class JewelleryChapterController {


    @Autowired
    private JewelleryChapterService chapterService;

    @GetMapping("getChapterVideo/{jewelleryId}")
    public R getChapterVideo(@PathVariable String jewelleryId){
        List<ChapterVo> list =  chapterService.getChapterVideoByJewelleryId(jewelleryId);
        return R.succeed().data("allChapterVideo",list);
    }
    //添加大纲
    @PostMapping("addChapter")
    public R addChapter(@RequestBody JewelleryChapter jewelleryChapter){
        chapterService.save(jewelleryChapter);
        return R.succeed();
    }
    //根据id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        JewelleryChapter jewelleryChapter = chapterService.getById(chapterId);
        return R.succeed().data("chapter",jewelleryChapter);
    }
    //修改大纲
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody JewelleryChapter jewelleryChapter){
        chapterService.updateById(jewelleryChapter);
        return R.succeed();
    }
    //删除的方法
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        boolean flag =  chapterService.deleteChapter(chapterId);
        if (flag){
            return R.succeed();
        }else {
            return R.failed();
        }

    }
}

