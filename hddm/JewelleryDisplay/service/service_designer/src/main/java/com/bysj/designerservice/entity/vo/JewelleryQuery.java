package com.bysj.designerservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Jewellery查询对象", description = "珠宝查询对象封装")
@Data
public class JewelleryQuery {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "珠宝名称")
    private String title;

    @ApiModelProperty(value = "设计师id")
    private String designerId;

    @ApiModelProperty(value = "一级类别id")
    private String classofyParentId;

    @ApiModelProperty(value = "二级类别id")
    private String classofyId;
}
