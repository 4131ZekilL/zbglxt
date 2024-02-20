package com.bysj.designerservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.commonutils.JwtUtils;
import com.bysj.commonutils.R;
import com.bysj.commonutils.vo.Member;
import com.bysj.designerservice.client.UcenterClient;
import com.bysj.designerservice.entity.JewelleryComment;
import com.bysj.designerservice.service.JewelleryCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jewelleryservice/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private JewelleryCommentService commentService;
    @PostMapping("addComment")
    public R addComment(@RequestBody JewelleryComment jewelleryComment, HttpServletRequest request){
        String  memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            return R.failed().message("请登录");
        }
        jewelleryComment.setMemberId(memberId);
        Member member = ucenterClient.getInfo(memberId);
        jewelleryComment.setNickname(member.getNickname());
        jewelleryComment.setAvatar(member.getAvatar());
        commentService.save(jewelleryComment);
        return R.succeed();
    }

    @GetMapping("{page}/{limit}")
    public R index(@PathVariable Long page, @PathVariable Long limit, String jewelleryId){
        Page<JewelleryComment> pageParam = new Page<>(page,limit);
        QueryWrapper<JewelleryComment> wrapper = new QueryWrapper<>();
        wrapper.eq("jewellery_id",jewelleryId);
        commentService.page(pageParam,wrapper);
        List<JewelleryComment> commentList = pageParam.getRecords();
        Map<String,Object> map = new HashMap<>();
        map.put("items", commentList);
        map.put("current", pageParam.getCurrent());
        map.put("pages", pageParam.getPages());
        map.put("size", pageParam.getSize());
        map.put("total", pageParam.getTotal());
        map.put("hasNext", pageParam.hasNext());
        map.put("hasPrevious", pageParam.hasPrevious());
        return R.succeed().data(map);
    }
}
