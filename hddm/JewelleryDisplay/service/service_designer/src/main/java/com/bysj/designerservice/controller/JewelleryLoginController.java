package com.bysj.designerservice.controller;

import com.bysj.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jewelleryservice/user")
@CrossOrigin//解决跨域问题
public class JewelleryLoginController {

    //login
    @PostMapping("login")
    public R login(){
        return R.succeed().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info(){
        return R.succeed().data("roles","[admin]").data("name","admin").data("avatar","\n" +
                "https://bysj-1024.oss-cn-beijing.aliyuncs.com/2023/03/01/ac8aa23aee434daf9e74fd54f3079700hmbb1.jpg");
    }
}
