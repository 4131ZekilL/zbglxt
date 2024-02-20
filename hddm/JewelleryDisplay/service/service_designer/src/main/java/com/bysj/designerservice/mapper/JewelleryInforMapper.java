package com.bysj.designerservice.mapper;

import com.bysj.designerservice.entity.JewelleryInfor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bysj.designerservice.entity.frontvo.JewelleryFrontInfoVo;
import com.bysj.designerservice.entity.vo.JewelleryPublishVo;

/**
 * <p>
 * 珠宝 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
public interface JewelleryInforMapper extends BaseMapper<JewelleryInfor> {

    public JewelleryPublishVo getPublishJewellery(String jewelleryId);


    JewelleryFrontInfoVo getBaseJewelleryInfo(String jewelleryId);
}
