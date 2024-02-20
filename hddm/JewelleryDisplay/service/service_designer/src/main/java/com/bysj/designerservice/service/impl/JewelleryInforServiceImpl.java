package com.bysj.designerservice.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.designerservice.entity.JewelleryDescription;
import com.bysj.designerservice.entity.JewelleryInfor;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontInfoVo;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontVo;
import com.bysj.designerservice.entity.vo.JewelleryInfoVo;
import com.bysj.designerservice.entity.vo.JewelleryPublishVo;
import com.bysj.designerservice.entity.vo.JewelleryQuery;
import com.bysj.designerservice.mapper.JewelleryInforMapper;
import com.bysj.designerservice.service.JewelleryChapterService;
import com.bysj.designerservice.service.JewelleryDescriptionService;
import com.bysj.designerservice.service.JewelleryInforService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.designerservice.service.JewelleryVideoService;
import com.bysj.servicebase.exceptionHandler.ZDYException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 珠宝 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@Service
public class JewelleryInforServiceImpl extends ServiceImpl<JewelleryInforMapper, JewelleryInfor> implements JewelleryInforService {

    @Autowired
    private JewelleryDescriptionService jewelleryDescriptionService;
    @Autowired
    public JewelleryVideoService jewelleryVideoService;
    @Autowired
    public JewelleryChapterService jewelleryChapterService;
    //添加珠宝基本信息
    @Override
    public String saveJewelleryInfo(JewelleryInfoVo jewelleryInfoVo) {
        JewelleryInfor jewelleryInfor = new JewelleryInfor();
        BeanUtils.copyProperties(jewelleryInfoVo,jewelleryInfor);
        //向珠宝表添加珠宝基本信息
        int insert = baseMapper.insert(jewelleryInfor);

        if (insert<=0){
            throw new ZDYException(10001,"添加珠宝信息失败！");
        }
        String jid = jewelleryInfor.getId();

        //向珠宝简介信息表添加数据
        JewelleryDescription jewelleryDescription = new JewelleryDescription();
        jewelleryDescription.setDescription(jewelleryInfoVo.getDescription());
        jewelleryDescription.setId(jid);
        jewelleryDescriptionService.save(jewelleryDescription);

        return jid;
    }

    @Override
    public JewelleryInfoVo getJewelleryInfo(String jewelleryId) {

        //查询珠宝表
        JewelleryInfor jewelleryInfor = baseMapper.selectById(jewelleryId);
        JewelleryInfoVo jewelleryInfoVo = new JewelleryInfoVo();
        BeanUtils.copyProperties(jewelleryInfor,jewelleryInfoVo);
        //查询描述表
        JewelleryDescription jewelleryDescription = jewelleryDescriptionService.getById(jewelleryId);
        jewelleryInfoVo.setDescription(jewelleryDescription.getDescription());

        return jewelleryInfoVo;

    }

    @Override
    public void updateJewelleryInfo(JewelleryInfoVo jewelleryInfoVo) {
        //修改珠宝表
        JewelleryInfor jewelleryInfor = new JewelleryInfor();
        BeanUtils.copyProperties(jewelleryInfoVo,jewelleryInfor);
        int update = baseMapper.updateById(jewelleryInfor);
        if (update==0){
            throw new ZDYException(10001,"修改珠宝信息失败！");
        }
        //修改珠宝简介表
        JewelleryDescription description = new JewelleryDescription();
        description.setId(jewelleryInfoVo.getId());
        description.setDescription((jewelleryInfoVo.getDescription()));
        jewelleryDescriptionService.updateById(description);

    }

    @Override
    public JewelleryPublishVo publishJewelleryInfo(String id) {
        JewelleryPublishVo publishJewellery = baseMapper.getPublishJewellery(id);
        return publishJewellery;
    }

    @Override
    public void pageListJewellery(Page<JewelleryInfor> pageParam, JewelleryQuery jewelleryQuery) {
        QueryWrapper<JewelleryInfor> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (jewelleryQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = jewelleryQuery.getTitle();
        String designerId = jewelleryQuery.getDesignerId();
        String classofyParentId = jewelleryQuery.getClassofyParentId();
        String classofyId = jewelleryQuery.getClassofyId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(designerId) ) {
            queryWrapper.eq("designer_id", designerId);
        }

        if (!StringUtils.isEmpty(classofyParentId)) {
            queryWrapper.ge("classofy_parent_id", classofyParentId);
        }

        if (!StringUtils.isEmpty(classofyId)) {
            queryWrapper.ge("classofy_id", classofyId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public void removeJewellery(String jewelleryId) {
        jewelleryVideoService.removeVideoByCourseId(jewelleryId);
        jewelleryDescriptionService.removeById(jewelleryId);
        int result = baseMapper.deleteById(jewelleryId);
        if (result==0){
            throw new ZDYException(10001,"删除失败");
        }
    }

    @Override
    public List<JewelleryInfor> selectByDesignerId(Long id) {
        QueryWrapper<JewelleryInfor> queryWrapper = new QueryWrapper<JewelleryInfor>();

        queryWrapper.eq("designer_id", id);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");

        List<JewelleryInfor> jewellery = baseMapper.selectList(queryWrapper);
        return jewellery;
    }

    @Override
    public Map<String, Object> getFrontJewelleryList(Page<JewelleryInfor> pageParam, JewelleryFrontVo jewelleryQuery) {
        QueryWrapper<JewelleryInfor> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(jewelleryQuery.getClassofyParentId())) {
            queryWrapper.eq("classofy_parent_id", jewelleryQuery.getClassofyParentId());
        }

        if (!StringUtils.isEmpty(jewelleryQuery.getClassofyId())) {
            queryWrapper.eq("classofy_id", jewelleryQuery.getClassofyId());
        }

        if (!StringUtils.isEmpty(jewelleryQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringUtils.isEmpty(jewelleryQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(jewelleryQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        baseMapper.selectPage(pageParam, queryWrapper);

        List<JewelleryInfor> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public JewelleryFrontInfoVo getBaseJewelleryInfo(String jewelleryId) {

        return baseMapper.getBaseJewelleryInfo(jewelleryId);
    }


}
