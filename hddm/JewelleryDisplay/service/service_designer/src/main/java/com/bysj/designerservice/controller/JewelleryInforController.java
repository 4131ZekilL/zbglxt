package com.bysj.designerservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.JewelleryInfor;
import com.bysj.designerservice.entity.vo.JewelleryInfoVo;
import com.bysj.designerservice.entity.vo.JewelleryPublishVo;
import com.bysj.designerservice.entity.vo.JewelleryQuery;
import com.bysj.designerservice.service.JewelleryInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 珠宝 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@RestController
@RequestMapping("/jewelleryservice/jewellery")
@CrossOrigin
public class JewelleryInforController {

    @Autowired
    private JewelleryInforService jewelleryInforService;

    @PostMapping("addJewelleryInfo")
    public R addJewelleryInfo(@RequestBody JewelleryInfoVo jewelleryInfoVo){
        //返回添加之后的珠宝id，为了后续上传使用
       String id =  jewelleryInforService.saveJewelleryInfo(jewelleryInfoVo);
        return R.succeed().data("jewelleryId",id);
    }
    //根据id查询珠宝基本信息
    @GetMapping("getJewelleryInfo/{jewelleryId}")
    public R getJewelleryInfo(@PathVariable String jewelleryId){
        JewelleryInfoVo jewelleryInfoVo =  jewelleryInforService.getJewelleryInfo(jewelleryId);
        return R.succeed().data("jewelleryInfoVo",jewelleryInfoVo);
    }

    //修改珠宝信息
    @PostMapping("updateJewelleryInfo")
    public R updateJewelleryInfo(@RequestBody JewelleryInfoVo jewelleryInfoVo){
        jewelleryInforService.updateJewelleryInfo(jewelleryInfoVo);
        return R.succeed();
    }
    //根据id查询珠宝确认信息
    @GetMapping("getPublishJewelleryInfo/{id}")
    public R getPublishJewelleryInfo(@PathVariable String id){
       JewelleryPublishVo jewelleryPublishVo= jewelleryInforService.publishJewelleryInfo(id);
       return  R.succeed().data("publishJewellery",jewelleryPublishVo);
    }
    //珠宝发布状态
    @PostMapping("updateJewelleryStatus/{id}")
    public R updateJewelleryStatus(@PathVariable String id){
        JewelleryInfor jewelleryInfor = new JewelleryInfor();
        jewelleryInfor.setId(id);
        jewelleryInfor.setStatus("Normal");
        jewelleryInforService.updateById(jewelleryInfor);
        return R.succeed();
    }

    //珠宝列表
    @PostMapping("pageListJewellery/{page}/{limit}")
    public R pageListJewellery(@PathVariable Long page, @PathVariable Long limit,JewelleryQuery jewelleryQuery){
        Page<JewelleryInfor> pageParam = new Page<>(page,limit);
        jewelleryInforService.pageListJewellery(pageParam,jewelleryQuery);

        long total = pageParam.getTotal();
        List<JewelleryInfor> records = pageParam.getRecords();
        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);
        return R.succeed().data(map);
    }
    //删除课程
    @DeleteMapping("{jewelleryId}")
    public R deleteJewellery(@PathVariable String jewelleryId){
        jewelleryInforService.removeJewellery(jewelleryId);
        return R.succeed();
    }

}

