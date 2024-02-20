package com.bysj.designerservice.controller;


import com.bysj.commonutils.R;
import com.bysj.designerservice.entity.classofy.OneClassofy;
import com.bysj.designerservice.service.JewelleryChapterService;
import com.bysj.designerservice.service.JewelleryClassofyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 珠宝种类 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/jewelleryservice/classofy")
@CrossOrigin
public class JewelleryClassofyController {
    @Autowired
    private JewelleryClassofyService classofyService;


    //添加珠宝分类
    //获取上传过来文件，把文件内容读取出来

    @PostMapping("addClassofy")
    public R addClassofy(MultipartFile file){
        //上传过来excel文件
        classofyService.saveClassofy(file,classofyService);
        return R.succeed();
    }
    //课程分类列表（树形）
    @GetMapping("getAllClassofy")
    public R getAllClassofy(){
        List<OneClassofy>list =  classofyService.getAllOneTwoClassofy();
        return R.succeed().data("list",list);
    }


}

