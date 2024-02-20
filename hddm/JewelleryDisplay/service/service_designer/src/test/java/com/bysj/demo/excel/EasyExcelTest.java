package com.bysj.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelTest {
    public static void main(String[] args) {
        //实现excel写的操作
        //1.设置写入文件夹地址和excel文件名称
//        String filename = "D:\\write.xlsx";

        //调用easyexcel
//        EasyExcel.write(filename,DemoData.class).sheet("用户列表").doWrite(getData());

        //实现excel读操作
        String filename = "D:\\write.xlsx";

        EasyExcel.read(filename, DemoData.class,new ExcelListener()).sheet().doRead();
    }

    //创建方法返回list集合
    private static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setCno(i);
            data.setCname("jack"+i);
            list.add(data);
        }
        return list;
    }
}
