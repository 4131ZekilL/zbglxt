package com.bysj.designerservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.designerservice.entity.JewelleryInfor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontInfoVo;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontVo;
import com.bysj.designerservice.entity.vo.JewelleryInfoVo;
import com.bysj.designerservice.entity.vo.JewelleryPublishVo;
import com.bysj.designerservice.entity.vo.JewelleryQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 珠宝 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
public interface JewelleryInforService extends IService<JewelleryInfor> {

    String saveJewelleryInfo(JewelleryInfoVo jewelleryInfoVo);

    JewelleryInfoVo getJewelleryInfo(String jewelleryId);

    void updateJewelleryInfo(JewelleryInfoVo jewelleryInfoVo);

    JewelleryPublishVo publishJewelleryInfo(String id);

    void pageListJewellery(Page<JewelleryInfor>pageParam, JewelleryQuery jewelleryQuery);

    void removeJewellery(String jewelleryId);

    List<JewelleryInfor> selectByDesignerId(Long id);


    Map<String, Object> getFrontJewelleryList(Page<JewelleryInfor> pageParam, JewelleryFrontVo jewelleryQuery);

    JewelleryFrontInfoVo getBaseJewelleryInfo(String jewelleryId);
}
