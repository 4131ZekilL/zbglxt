package com.bysj.designerservice.entity.vo;

import lombok.Data;

@Data
public class JewelleryPublishVo {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String cover;
    private String classofyLevelOne;
    private String classofyLevelTwo;
    private String designerName;
    private String price;//只用于显示
}
