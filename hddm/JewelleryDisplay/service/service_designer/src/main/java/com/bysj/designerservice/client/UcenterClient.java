package com.bysj.designerservice.client;
import com.bysj.commonutils.vo.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    @PostMapping("/jewellerylogin/apimember/getInfo/{id}")
    public Member getInfo(@PathVariable String id);
}
