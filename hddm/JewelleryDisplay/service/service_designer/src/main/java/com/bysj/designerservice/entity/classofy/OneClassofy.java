package com.bysj.designerservice.entity.classofy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneClassofy {
    private String id;
    private String title;

    private List<TwoClassofy> children = new ArrayList<>();
}
