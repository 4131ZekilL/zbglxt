package com.bysj.servicebase.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//生成有参数构造方法
@NoArgsConstructor//生成无参数构造方法
public class ZDYException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;//状态码

    private String msg;//异常信息
}
