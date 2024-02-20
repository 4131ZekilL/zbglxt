package com.bysj.designerservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.designerservice.entity.JewelleryDesigner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 设计师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-10-10
 */
public interface JewelleryDesignerService extends IService<JewelleryDesigner> {

    Map<String, Object> getFrontDesignerList(Page<JewelleryDesigner> pageParam);


}
