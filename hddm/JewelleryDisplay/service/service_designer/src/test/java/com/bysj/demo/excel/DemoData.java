package com.bysj.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(value = "用户编号",index = 0)
    private Integer cno;

    @ExcelProperty(value = "用户姓名",index = 1)
    private String cname;

}
