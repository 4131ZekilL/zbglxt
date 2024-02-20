package com.bysj.ucenter.service;

import com.bysj.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bysj.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-03-18
 */
public interface MemberService extends IService<Member> {

    String login(Member loginVo);

    void register(RegisterVo registerVo);

}
