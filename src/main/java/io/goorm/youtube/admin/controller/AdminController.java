package io.goorm.youtube.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {


    //리스트
    @GetMapping("/admins")
    public String  list(Model model) {

        //model.addAttribute("posts", boardService.getBoards());

        return "admin/admin/list";
    }

    //뷰
    @GetMapping("/admins/{adminSeq}")
    public String  view(@PathVariable Long adminSeq, Model model) {

        //model.addAttribute("posts", boardService.getBoards());

        return "admin/admin/view";
    }

    //생성화면
    @GetMapping("/admins/create")
    public String  create(Model model) {

        //model.addAttribute("posts", boardService.getBoards());

        return "admin/admin/create";
    }

    //수정화면
    @GetMapping("/admins/modify")
    public String  modify(Model model) {

        //model.addAttribute("posts", boardService.getBoards());

        return "admin/admin/modify";
    }


}
