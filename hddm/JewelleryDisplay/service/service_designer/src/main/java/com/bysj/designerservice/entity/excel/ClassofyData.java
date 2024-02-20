package com.bysj.designerservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ClassofyData {
    @ExcelProperty(index = 0)
    private String oneClassofyName;

    @ExcelProperty(index = 1)
    private String twoClassofyName;

}
