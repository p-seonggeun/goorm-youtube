package io.goorm.youtube.admin.controller;

import io.goorm.youtube.service.MemberService;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Member;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller("adminMemberController")
@RequestMapping("/mgr")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //리스트
    @GetMapping("/members")
    public String  list(DefaultVO defaultVO, Model model) {

        model.addAttribute("posts", memberService.findAll(defaultVO));
        model.addAttribute("title", "사용자관라-리스트" );
        model.addAttribute("page", defaultVO.getPage());
        model.addAttribute("totalPages", defaultVO.getTotalPages());

        return "mgr/member/list";
    }

    //뷰
    @GetMapping("/members/{memberSeq}")
    public String  get(@PathVariable Long memberSeq, Model model) {

        Member member = memberService.find(memberSeq);
        member.setMemberPw("");

        model.addAttribute("post", member);
        model.addAttribute("title", "사용자관라-상세화면" );
        
        return "mgr/member/view";
    }

}




