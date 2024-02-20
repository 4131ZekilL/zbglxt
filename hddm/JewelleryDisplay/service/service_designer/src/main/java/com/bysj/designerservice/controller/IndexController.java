package com.bysj.designerservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.JewelleryDesigner;
import com.bysj.designerservice.entity.JewelleryInfor;
import com.bysj.designerservice.service.JewelleryDesignerService;
import com.bysj.designerservice.service.JewelleryInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jewelleryservice/index")
@CrossOrigin
public class IndexController {
    @Autowired
    private JewelleryDesignerService designerService;
    @Autowired
    private JewelleryInforService jewelleryService;

    //查询前8条热门珠宝，查询前4条设计师
    @GetMapping("index")
    public R index() {
        //查询前8条热门珠宝
        QueryWrapper<JewelleryInfor> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("buy_count");
        wrapper.last("limit 8");
        List<JewelleryInfor> jewelleryList = jewelleryService.list(wrapper);

        //查询前4条设计师
        QueryWrapper<JewelleryDesigner> wrapperDesigner = new QueryWrapper<>();
        wrapperDesigner.orderByDesc("id");
        wrapperDesigner.last("limit 4");
        List<JewelleryDesigner> designerList = designerService.list(wrapperDesigner);

        return R.succeed().data("jewelleryList",jewelleryList).data("designerList",designerList);
    }
}
