package com.bysj.ucenter.controller;

import com.bysj.commonutils.JwtUtils;
import com.bysj.commonutils.R;

import com.bysj.ucenter.entity.Member;
import com.bysj.ucenter.entity.vo.RegisterVo;
import com.bysj.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jewellerylogin/apimember")
@CrossOrigin
public class MemberApiController {
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody Member loginVo) {
        String token = memberService.login(loginVo);
        return R.succeed().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.succeed();
    }
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){

            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            Member member = memberService.getById(memberId);

            return R.succeed().data("userInfo", member);
    }
    @PostMapping("getInfo/{id}")
    public Member getInfo(@PathVariable String id){
        Member member = memberService.getById(id);
        return member;
    }
}
