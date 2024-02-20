package com.bysj.designerservice.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 珠宝
 * </p>
 *
 * @author testjava
 * @since 2023-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="JewelleryInfor对象", description="珠宝")
public class JewelleryInfor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "珠宝ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "珠宝ID")
    private String designerId;

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

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "珠宝状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "珠宝分类id")
    private String classofyId;

    @ApiModelProperty(value = "珠宝分类父级id")
    private String classofyParentId;


}
