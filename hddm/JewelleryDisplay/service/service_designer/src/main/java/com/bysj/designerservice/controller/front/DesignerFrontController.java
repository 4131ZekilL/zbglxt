package com.bysj.designerservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.JewelleryDesigner;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontVo;
import com.bysj.designerservice.service.JewelleryDesignerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jewelleryservice/designerfront")
@CrossOrigin
public class DesignerFrontController {
    @Autowired
    private JewelleryDesignerService jewelleryDesignerService;

    @ApiOperation(value = "分页设计师列表")
    @GetMapping(value = "pageList/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<JewelleryDesigner> pageParam = new Page<JewelleryDesigner>(page, limit);

        Map<String, Object> map = jewelleryDesignerService.getFrontDesignerList(pageParam);

        return  R.succeed().data(map);
    }
}
