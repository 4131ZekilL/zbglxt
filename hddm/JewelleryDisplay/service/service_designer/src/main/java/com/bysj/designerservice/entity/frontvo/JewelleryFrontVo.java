package com.bysj.designerservice.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JewelleryFrontVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "珠宝名称")
    private String title;

    @ApiModelProperty(value = "设计师id")
    private String designerId;

    @ApiModelProperty(value = "一级类别id")
    private String classofyParentId;

    @ApiModelProperty(value = "二级类别id")
    private String classofyId;

    @ApiModelProperty(value = "销量排序")
    private String buyCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String gmtCreateSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
