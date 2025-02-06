package io.goorm.youtube.admin.controller;

import io.goorm.youtube.service.MemberService;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import io.goorm.youtube.vo.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String  list(DefaultVO defaultVO, Model model, HttpSession session) {
        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }
        
        model.addAttribute("posts", memberService.findAll(defaultVO));
        model.addAttribute("title", "사용자관라-리스트" );
        model.addAttribute("page", defaultVO.getPage());
        model.addAttribute("totalPages", defaultVO.getTotalPages());

        return "mgr/member/list";
    }

    //뷰
    @GetMapping("/members/{memberSeq}")
    public String  get(@PathVariable Long memberSeq, Model model, HttpSession session) {
        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }

        Member member = memberService.find(memberSeq);
        member.setMemberPw("");

        model.addAttribute("post", member);
        model.addAttribute("title", "사용자관라-상세화면" );
        
        return "mgr/member/view";
    }

    //사용여부 변경
    @GetMapping("/members/{memberSeq}/useyn")
    public String updateUseYN(@PathVariable("memberSeq") Long memberSeq, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }

        Member member = memberService.find(memberSeq);

        if (member.getUseYn().equals("Y")) {
            member.setUseYn("N");
        } else {
            member.setUseYn("Y");
        }

        memberService.updateUseYn(member);

        redirectAttributes.addAttribute("adminSeq", member.getMemberSeq());
        redirectAttributes.addFlashAttribute("msg", "사용여부 수정에 성공하였습니다.");

        return "redirect:/mgr/members";

    }

}




