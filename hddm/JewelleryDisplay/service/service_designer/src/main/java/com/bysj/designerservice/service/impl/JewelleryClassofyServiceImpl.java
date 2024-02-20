package com.bysj.designerservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.designerservice.entity.JewelleryClassofy;
import com.bysj.designerservice.entity.classofy.OneClassofy;
import com.bysj.designerservice.entity.classofy.TwoClassofy;
import com.bysj.designerservice.entity.excel.ClassofyData;
import com.bysj.designerservice.listener.ClassofyExcelListener;
import com.bysj.designerservice.mapper.JewelleryClassofyMapper;
import com.bysj.designerservice.service.JewelleryClassofyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 珠宝种类 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-03-02
 */
@Service
public class JewelleryClassofyServiceImpl extends ServiceImpl<JewelleryClassofyMapper, JewelleryClassofy> implements JewelleryClassofyService {

    //添加珠宝分类
    @Override
    public void saveClassofy(MultipartFile file,JewelleryClassofyService classofyService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();

            EasyExcel.read(in, ClassofyData.class,new ClassofyExcelListener(classofyService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneClassofy> getAllOneTwoClassofy() {
        //1 查询出所有一级分类
        QueryWrapper<JewelleryClassofy> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<JewelleryClassofy> oneClassofyList = baseMapper.selectList(wrapperOne);

        //2 查询出所有二级分类
        QueryWrapper<JewelleryClassofy> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id","0");
        List<JewelleryClassofy> twoClassofyList = baseMapper.selectList(wrapperTwo);

        //创建list集合，最终封装数据
        List<OneClassofy> finalClassofyList= new ArrayList<>();

        //3 封装一级分类
        for (int i = 0; i < oneClassofyList.size() ; i++) {
            JewelleryClassofy jewelleryClassofy = oneClassofyList.get(i);
            OneClassofy oneClassofy = new OneClassofy();
//            oneClassofy.setId(jewelleryClassofy.getId());
//            oneClassofy.setTitle(jewelleryClassofy.getTitle());
            BeanUtils.copyProperties(jewelleryClassofy,oneClassofy);
            finalClassofyList.add(oneClassofy);

            //创建list集合封装每个一级
            List<TwoClassofy> twoFinalList = new ArrayList<>();
            for (int j = 0; j < twoClassofyList.size(); j++) {
                JewelleryClassofy tClassofy = twoClassofyList.get(j);
                if (tClassofy.getParentId().equals(jewelleryClassofy.getId())){
                    TwoClassofy twoClassofy = new TwoClassofy();
                    BeanUtils.copyProperties(tClassofy,twoClassofy);
                    twoFinalList.add(twoClassofy);
                }
            }
            oneClassofy.setChildren(twoFinalList);
        }
        //4 封装二级分类
        return finalClassofyList;
    }
}
