package com.bysj.designerservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设计师
 * </p>
 *
 * @author testjava
 * @since 2022-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JewelleryDesigner对象", description="设计师")
public class JewelleryDesigner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设计师ID")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "设计师姓名")
    private String name;

    @ApiModelProperty(value = "设计师简介")
    private String intro;

    @ApiModelProperty(value = "设计师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级技师 2技师")
    private Integer level;

    @ApiModelProperty(value = "设计师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
