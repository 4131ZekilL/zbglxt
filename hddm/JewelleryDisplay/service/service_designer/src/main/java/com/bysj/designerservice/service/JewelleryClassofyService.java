package com.bysj.designerservice.service;

import com.bysj.designerservice.entity.JewelleryClassofy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bysj.designerservice.entity.classofy.OneClassofy;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 珠宝种类 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-03-02
 */
public interface JewelleryClassofyService extends IService<JewelleryClassofy> {

    //添加珠宝分类
    void saveClassofy(MultipartFile file,JewelleryClassofyService classofyService);

    //珠宝分类列表
    List<OneClassofy> getAllOneTwoClassofy();
}
