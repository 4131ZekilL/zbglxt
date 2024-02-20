package com.bysj.designerservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.JewelleryInfor;
import com.bysj.designerservice.entity.chapter.ChapterVo;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontInfoVo;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontVo;
import com.bysj.designerservice.service.JewelleryChapterService;
import com.bysj.designerservice.service.JewelleryInforService;
import com.bysj.designerservice.service.JewelleryVideoService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jewelleryservice/jewelleryfront")
@CrossOrigin
public class JewelleryFrontController {
    @Autowired
    private JewelleryInforService inforService;

    @Autowired
    private JewelleryChapterService chapterService;
    @PostMapping("pageList/{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)
                          @PathVariable Long page,

                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                          @PathVariable Long limit,

                      @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                          @RequestBody(required = false) JewelleryFrontVo jewelleryQuery){
        Page<JewelleryInfor> pageParam = new Page<>(page,limit);
        Map<String,Object> map = inforService.getFrontJewelleryList(pageParam,jewelleryQuery);
        return  R.succeed().data(map);
    }
    @GetMapping("getFrontJewelleryInfo/{jewelleryId}")
    public R getFrontJewelleryInfo(@PathVariable String jewelleryId){
        JewelleryFrontInfoVo frontInfoVo =  inforService.getBaseJewelleryInfo(jewelleryId);

        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByJewelleryId(jewelleryId);

        return R.succeed().data("frontInfoVo",frontInfoVo).data("chapterVideoList",chapterVideoList);
    }
}
