package com.bysj.ucenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.commonutils.JwtUtils;
import com.bysj.servicebase.exceptionHandler.ZDYException;
import com.bysj.ucenter.entity.Member;
import com.bysj.ucenter.entity.vo.RegisterVo;
import com.bysj.ucenter.mapper.MemberMapper;
import com.bysj.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.ucenter.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-03-18
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String login(Member member) {
        String mobile = member.getMobile();
        String password = member.getPassword();

        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new ZDYException(10001,"手机号或者密码为空，登录失败");
        }

        //获取会员
        Member mobileMember = baseMapper.selectOne(new QueryWrapper<Member>().eq("mobile", mobile));
        if(null == mobileMember) {
            throw new ZDYException(10001,"用户不存在，登录失败");
        }
        //校验密码
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new ZDYException(10001,"密码错误，登录失败");
        }

        //校验是否被禁用
        if(mobileMember.getIsDisabled()) {
            throw new ZDYException(10001,"用户被禁用，登录失败");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验参数
        if(StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(nickname) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new ZDYException(10001,"注册失败");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode =redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobleCode)) {
            throw new ZDYException(10001,"注册失败");
        }

        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<Member>().eq("mobile", mobile));
        if(count.intValue() > 0) {
            throw new ZDYException(10001,"手机号码已存在，注册失败！");
        }
        //添加注册信息到数据库
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }


}
