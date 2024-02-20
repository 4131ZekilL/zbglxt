package com.bysj.designerservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.designerservice.entity.JewelleryDesigner;
import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.JewelleryInfor;
import com.bysj.designerservice.entity.vo.DesignerQuery;
import com.bysj.designerservice.service.JewelleryDesignerService;
import com.bysj.designerservice.service.JewelleryInforService;
import com.bysj.servicebase.exceptionHandler.ZDYException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设计师 前端控制器
 * </p>
 *
 * @author zsn
 * @since 2022-10-10
 */
@RestController
@RequestMapping("/jewelleryservice/designer")
@Api(tags="设计师管理")
@CrossOrigin
public class JewelleryDesignerController {

    //访问地址：http://localhost:8001/jewelleryservice/designer/findAll
    //把service注入
    @Autowired
    private JewelleryDesignerService designerService;

    @Autowired
    private JewelleryInforService inforService;

    //1 查询设计师表所有数据
    //rest风格
    @ApiOperation(value = "所有设计师列表")
    @GetMapping("findAll")
    public R findAllDesigner(){
        //调用service的方法调用查询的方法
        List<JewelleryDesigner> list = designerService.list(null);
        return R.succeed().data("items",list);
    }
    //2.逻辑删除设计师的方法
    @ApiOperation(value = "逻辑删除设计师")
    @DeleteMapping("{id}")
    public R removeDesigner(@ApiParam(name = "id", value = "设计师ID", required = true) @PathVariable String id){
        boolean flag = designerService.removeById(id);
        if (flag){
            return R.succeed();
        }else {
            return R.failed();
        }
    }
    //3.分页查询设计师的方法
    @ApiOperation(value = "分页查询设计师")
    @GetMapping("pageDsigner/{current}/{limit}")
    public R pageListDesigner(@PathVariable long current ,@PathVariable long limit){
        //创建page对象
        Page<JewelleryDesigner> pageDesigner = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法的时候，底层封装，将分页所有数据封装到pageDesigner对象里面
        designerService.page(pageDesigner,null);

        long total = pageDesigner.getTotal();//总记录数
        List<JewelleryDesigner> records = pageDesigner.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.succeed().data(map);
    }
    //4.分页查询带条件组合的方法
    @ApiOperation(value = "分页查询带条件组合")
    @PostMapping("pageDesignerCondition/{current}/{limit}")
    public R pageDesignerCondition (@PathVariable long current , @PathVariable long limit,
                                       @RequestBody DesignerQuery designerQuery){
        //创建page对象
        Page<JewelleryDesigner> pageDesigner = new Page<>(current,limit);
        //构建条件
        QueryWrapper<JewelleryDesigner>wrapper = new QueryWrapper<>();
        //多条件组合查询
        //判断条件是否为空，如果不为空拼接条件
        String name = designerQuery.getName();
        Integer level = designerQuery.getLevel();
        String begin = designerQuery.getBegin();
        String end = designerQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }
        //调用方法实现条件查询分页
        designerService.page(pageDesigner,wrapper);
        long total = pageDesigner.getTotal();//总记录数
        List<JewelleryDesigner> records = pageDesigner.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.succeed().data(map);
    }
    //5.添加设计师接口的方法
    @ApiOperation(value = "添加设计师")
    @PostMapping("addDesigner")
    public R addDesginer(@RequestBody JewelleryDesigner jewelleryDesigner){
        boolean save = designerService.save(jewelleryDesigner);
        if (save){
            return R.succeed();
        }else {
            return R.failed();
        }
    }
    //6.根据讲设计师id进行查询
    @ApiOperation(value = "查询设计师")
    @GetMapping("getDesigner/{id}")
    public R getDesigner(@PathVariable Long id){

        JewelleryDesigner jewelleryDesigner = designerService.getById(id);
        //根据设计师id查询这个设计师的珠宝列表
        List<JewelleryInfor> jewelleryInforList = inforService.selectByDesignerId(id);
        return R.succeed().data("designer",jewelleryDesigner).data("jewelleryList",jewelleryInforList);
    }
    //7.根据id修改设计师
    @ApiOperation(value = "修改设计师")
    @PostMapping("updateDesigner")
    public R updateDesigner(@RequestBody JewelleryDesigner jewelleryDesigner){
        boolean flag = designerService.updateById(jewelleryDesigner);
        if (flag){
            return R.succeed();
        }else {
            return R.failed();
        }
    }
}

