package io.goorm.youtube.controller;

import io.goorm.youtube.commom.util.FileUploadUtil;
import io.goorm.youtube.service.VideoService;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import io.goorm.youtube.vo.domain.Video;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    //리스트
    @GetMapping("/videos")
    public String list(@ModelAttribute DefaultVO defaultVO, Model model) {

        model.addAttribute("posts", videoService.findAll(defaultVO));
        model.addAttribute("title", "비디오-리스트" );
        model.addAttribute("page", defaultVO.getPage());
        model.addAttribute("totalPages", defaultVO.getTotalPages());


        return "video/list";
    }

    //뷰
    @GetMapping("/videos/{videoSeq}")
    public String  get(@PathVariable("videoSeq") Long videoSeq, Model model) {

        model.addAttribute("posts", videoService.find(videoSeq));
        model.addAttribute("title", "비디오-상세조회" );

        return "video/view";
    }

    //생성화면
    @GetMapping("/videos/create")
    public String  createForm(Model model, HttpSession session) {
        log.debug("createForm");
        // 세션에서 로그인 정보 확인
        var member = session.getAttribute("member");

        if (member == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }

        // 로그인된 경우 생성 화면으로 이동
        model.addAttribute("title", "비디오-생성");

        return "video/create";
    }


    //생성
    @PostMapping("/videos")
    public String create(@Valid @ModelAttribute Video video,
                         @RequestParam("videoFile") MultipartFile videoFile,
                         @RequestParam("videoThumnailFile") MultipartFile videoThumbnailFile,
                         Model model) {

        log.debug("create");

        try {
            // 업로드된 파일 처리
            String thumbnailPath = FileUploadUtil.uploadFile(videoThumbnailFile, "thumbnail");
            String videoPath = FileUploadUtil.uploadFile(videoFile, "vod");

            // 업로드된 파일 경로를 엔티티에 설정
            video.setVideo(videoPath);
            video.setVideoThumnail(thumbnailPath);

            videoService.save(video);

            model.addAttribute("msg", "비디오가 성공적으로 등록 되었습니다.");

        } catch (Exception e) {
            log.debug(e.getMessage());
            model.addAttribute("msg", "비디오등록에 실패하였습니다.");
            return "redirect:/videos/create"; // 예외 발생시 등록 폼으로
        }
        
        return "redirect:/";
    }


    //수정화면
    @GetMapping("/videos/{videoSeq}/update")
    public String updateForm(@PathVariable("videoSeq") Long videoSeq, Model model) {

        model.addAttribute("posts", videoService.find(videoSeq));
        model.addAttribute("title", "비디오-수정" );

        return "video/update";
    }

    //수정
    @PostMapping("/videos/{videoSeq}")
    public String  update(@ModelAttribute Video video,
                          @PathVariable("videoSeq") Long videoSeq,
                          @RequestParam("videoFile") MultipartFile videoFile,
                          @RequestParam("videoThumnailFile") MultipartFile videoThumbnailFile,
                          Model model, RedirectAttributes redirectAttributes) {

        log.debug("update");

        try {
            // 업로드된 파일 처리
            String thumbnailPath = FileUploadUtil.uploadFile(videoThumbnailFile, "thumbnail");
            String videoPath = FileUploadUtil.uploadFile(videoFile, "vod");

            // 업로드된 파일 경로를 엔티티에 설정
            video.setVideo(videoPath);
            video.setVideoThumnail(thumbnailPath);

            videoService.update(video);

            model.addAttribute("msg", "비디오가 성공적으로 수정 되었습니다.");

        } catch (Exception e) {
            log.debug(e.getMessage());
            model.addAttribute("msg", "비디오수정에 실패하였습니다.");
            return "redirect:/videos/create"; // 예외 발생시 등록 폼으로
        }

        return "redirect:/";
    }

    //사용여부 변경
    @GetMapping("/videos/{videoSeq}/publishyn")
    public String  updateUseYN(@PathVariable("videoSeq") Long videoSeq, Model model, RedirectAttributes redirectAttributes) {

        Video video = videoService.find(videoSeq);

        if (video.getPublishYn() == 0) {
            video.setPublishYn(1);
        } else {
            video.setPublishYn(0);
        }

        videoService.updatePublishYn(video);

        redirectAttributes.addAttribute("adminSeq", video.getVideoSeq());
        redirectAttributes.addFlashAttribute("msg", "사용여부 수정에 성공하였습니다.");

        return "redirect:/videos";

    }
}
