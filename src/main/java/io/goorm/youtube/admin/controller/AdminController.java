package io.goorm.youtube.admin.controller;


import io.goorm.youtube.domain.Admin;
import io.goorm.youtube.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/mgr")
public class AdminController {

    AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //리스트
    @GetMapping("/admins")
    public String list(Model model) {

        model.addAttribute("posts", adminService.findAll());
        model.addAttribute("title", "관리자관라-리스트" );

        return "mgr/admin/list";
    }

    //뷰
    @GetMapping("/admins/{adminSeq}")
    public String  get(@PathVariable("adminSeq") Long adminSeq, Model model) {

        model.addAttribute("posts", adminService.find(adminSeq));
        model.addAttribute("title", "관리자관라-상세조회" );

        return "mgr/admin/view";
    }

    //생성화면
    @GetMapping("/admins/create")
    public String  createForm(Model model) {

        model.addAttribute("title", "관리자관라-생성" );

        return "mgr/admin/create";
    }


    //생성
    @PostMapping("/admins")
    public String create(@ModelAttribute Admin admin, Model model) {

        return "redirect:/mgr/admins";
    }


    //수정화면
    @GetMapping("/admins/{adminSeq}/update")
    public String updateForm(@PathVariable("adminSeq") Long adminSeq, Model model) {

        model.addAttribute("posts", adminService.find(adminSeq));
        model.addAttribute("title", "관리자관라-수정" );

        return "mgr/admin/update";
    }

    //수정
    @PostMapping("/admins/{adminSeq}")
    public String  update(@ModelAttribute Admin admin, Model model, RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("adminSeq", admin.getAdminSeq());
        redirectAttributes.addFlashAttribute("msg", "수정에 성공하였습니다.");

        return "redirect:/mgr/admins/{adminSeq}";

        //return "redirect:/mgr/admins/" + admin.getAdminSeq();
    }

}
