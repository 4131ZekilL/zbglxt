package com.bysj.designerservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.designerservice.entity.JewelleryClassofy;
import com.bysj.designerservice.entity.excel.ClassofyData;
import com.bysj.designerservice.service.JewelleryClassofyService;
import com.bysj.servicebase.exceptionHandler.ZDYException;

public class ClassofyExcelListener extends AnalysisEventListener<ClassofyData> {

    public JewelleryClassofyService classofyService;
    public ClassofyExcelListener(JewelleryClassofyService classofyService) {
        this.classofyService = classofyService;
    }
    public ClassofyExcelListener() {
    }
    //读取excel内容，一行意航渡
    @Override
    public void invoke(ClassofyData data, AnalysisContext analysisContext) {
        if (data==null){
            throw new ZDYException(10001,"文件数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断一级分类是否重复
        JewelleryClassofy exitOneClassofy = this.existOneSubject(classofyService, data.getOneClassofyName());
        if (exitOneClassofy == null){
            exitOneClassofy = new JewelleryClassofy();
            exitOneClassofy.setParentId("0");
            exitOneClassofy.setTitle(data.getOneClassofyName());
            classofyService.save(exitOneClassofy);
        }
        //获取一级分类id值
        String pid=exitOneClassofy.getId();
        JewelleryClassofy exitsTwoClassofy = this.existTwoSubject(classofyService, data.getTwoClassofyName(), pid);
        if (exitsTwoClassofy == null){
            exitsTwoClassofy = new JewelleryClassofy();
            exitsTwoClassofy.setParentId(pid);
            exitsTwoClassofy.setTitle(data.getTwoClassofyName());
            classofyService.save(exitsTwoClassofy);
        }

    }
    //判断一级分类不能重复添加
    private JewelleryClassofy existOneSubject(JewelleryClassofyService classofyService,String name){
        QueryWrapper<JewelleryClassofy> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",0);
        JewelleryClassofy oneClassofy = classofyService.getOne(wrapper);
        return oneClassofy;
    }

    //判断二级分类不能重复添加
    private JewelleryClassofy existTwoSubject(JewelleryClassofyService classofyService,String name,String pid){
        QueryWrapper<JewelleryClassofy> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        JewelleryClassofy twoClassofy = classofyService.getOne(wrapper);
        return twoClassofy;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
