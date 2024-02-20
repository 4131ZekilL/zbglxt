package com.bysj.designerservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JewelleryInfoVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "珠宝ID")
    private String id;

    @ApiModelProperty(value = "设计师ID")
    private String designerId;

    @ApiModelProperty(value = "珠宝分类ID")
    private String classofyId;

    @ApiModelProperty(value = "珠宝标题")
    private String title;

    @ApiModelProperty(value = "珠宝销售价格")
    private BigDecimal price;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "珠宝简介")
    private String description;

    @ApiModelProperty(value = "珠宝父类id")
    private String classofyParentId;
}
