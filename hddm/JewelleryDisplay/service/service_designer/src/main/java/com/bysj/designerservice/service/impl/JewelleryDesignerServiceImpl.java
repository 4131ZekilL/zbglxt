package com.bysj.designerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.designerservice.entity.JewelleryDesigner;
import com.bysj.designerservice.mapper.JewelleryDesignerMapper;
import com.bysj.designerservice.service.JewelleryDesignerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 设计师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-10-10
 */
@Service
public class JewelleryDesignerServiceImpl extends ServiceImpl<JewelleryDesignerMapper, JewelleryDesigner> implements JewelleryDesignerService {

    @Override
    public Map<String, Object> getFrontDesignerList(Page<JewelleryDesigner> pageParam) {
        QueryWrapper<JewelleryDesigner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        baseMapper.selectPage(pageParam, queryWrapper);

        List<JewelleryDesigner> records = pageParam.getRecords();
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


}
