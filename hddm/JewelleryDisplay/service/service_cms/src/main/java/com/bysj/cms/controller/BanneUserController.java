package com.bysj.cms.controller;


import com.bysj.cms.entity.CrmBanner;
import com.bysj.cms.service.CrmBannerService;
import com.bysj.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-17
 */
@RestController
@RequestMapping("/jewellerycms/banneruser")
@CrossOrigin
public class BanneUserController {
    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        return R.succeed().data("bannerList", list);
    }
}

