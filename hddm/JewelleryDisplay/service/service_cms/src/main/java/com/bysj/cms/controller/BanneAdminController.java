package com.bysj.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.cms.entity.CrmBanner;
import com.bysj.cms.service.CrmBannerService;
import com.bysj.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-17
 */
@RestController
@RequestMapping("/jewellerycms/banneradmin")
@CrossOrigin
public class BanneAdminController {

    @Autowired
    private CrmBannerService crmBannerService;
    //分页查询banner
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page,@PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        crmBannerService.page(pageBanner,null);
        return R.succeed().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }
    //添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.save(crmBanner);
        return R.succeed();
    }
    @PutMapping("updateById")
    public R updateById(@RequestBody CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.succeed();
    }
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return R.succeed();
    }
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.succeed().data("item", banner);
    }

}

