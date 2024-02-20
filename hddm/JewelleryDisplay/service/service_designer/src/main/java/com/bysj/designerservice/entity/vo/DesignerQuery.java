package com.bysj.designerservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DesignerQuery {
    @ApiModelProperty(value = "设计师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级技师 2技师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2022-01-01 00:00:00")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2022-12-31 23:59:59")
    private String end;
}
