package com.bysj.designerservice.entity.frontvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class JewelleryFrontInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty(value = "珠宝标题")
    private String title;

    @ApiModelProperty(value = "珠宝销售价格")
    private BigDecimal price;

    @ApiModelProperty(value = "珠宝封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "珠宝简介")
    private String description;

    @ApiModelProperty(value = "设计师ID")
    private String designerId;

    @ApiModelProperty(value = "设计师姓名")
    private String designerName;

    @ApiModelProperty(value = "设计师资历,一句话说明设计师")
    private String intro;

    @ApiModelProperty(value = "设计师头像")
    private String avatar;

    @ApiModelProperty(value = "珠宝一级分类ID")
    private String classofyLevelOneId;

    @ApiModelProperty(value = "类别名称")
    private String classofyLevelOne;

    @ApiModelProperty(value = "珠宝二级分类ID")
    private String classofyLevelTwoId;

    @ApiModelProperty(value = "类别名称")
    private String classofyLevelTwo;
}
