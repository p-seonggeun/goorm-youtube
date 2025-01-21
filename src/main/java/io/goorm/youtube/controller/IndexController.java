package io.goorm.youtube.controller;

import io.goorm.youtube.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class IndexController {

    @Autowired
    private VideoService videoService;

    //리스트
    @GetMapping("/")
    public String  list(Model model) {

        model.addAttribute("videos", videoService.getVideoForIndex());

        return "index";
    }

}
