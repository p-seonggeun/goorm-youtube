package io.goorm.youtube.admin.controller;


import io.goorm.youtube.commom.util.PasswordUtil;
import io.goorm.youtube.service.AdminService;
import io.goorm.youtube.vo.DefaultVO;
import io.goorm.youtube.vo.domain.Admin;
import jakarta.servlet.http.HttpSession;
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

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //로그인폼
    @GetMapping("")
    public String login( Model model) {

        model.addAttribute("title", "관리자-로그인" );

        return "mgr/admin/login";
    }

    //로그인
        @PostMapping("/login")
    public String login(@ModelAttribute Admin admin, HttpSession session, Model model) {


        Admin admins = adminService.login(admin);

        if ( admin != null && validateLogin(admins.getAdminPw(), admin.getAdminPw()) ) {

            log.debug("성공");
            session.setAttribute("admin", admins);

            return "redirect:/mgr/admins";

        } else {
            log.debug("실패");
            model.addAttribute("msg", "로그인에 실패하였습니다. 아이디와 비밀번호를 확인해주세요");

            return "mgr/admin/login";

        }

    }

    public boolean validateLogin(String storedPassword, String password) {

        log.debug("storedPassword : " + storedPassword);
        log.debug("password : " + password);

        return storedPassword != null && PasswordUtil.matches(password, storedPassword);
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {

        session.invalidate();

        return "redirect:/mgr";
    }

    //리스트
    @GetMapping("/admins")
    public String list(@ModelAttribute DefaultVO defaultVO, Model model, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }


        model.addAttribute("posts", adminService.findAll(defaultVO));
        model.addAttribute("title", "관리자관라-리스트" );
        model.addAttribute("page", defaultVO.getPage());
        model.addAttribute("totalPages", defaultVO.getTotalPages());

        return "mgr/admin/list";
    }

    //뷰
    @GetMapping("/admins/{adminSeq}")
    public String  get(@PathVariable("adminSeq") Long adminSeq, Model model, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }

        Admin admin = adminService.find(adminSeq);
        admin.setAdminPw("");

        model.addAttribute("post", adminService.find(adminSeq));
        model.addAttribute("title", "관리자관라-상세조회" );

        return "mgr/admin/view";
    }

    //생성화면
    @GetMapping("/admins/create")
    public String  createForm(Model model, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }

        model.addAttribute("title", "관리자관라-생성" );

        return "mgr/admin/create";
    }


    //생성
    @PostMapping("/admins")
    public String create(@ModelAttribute Admin admin, Model model, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }
        String encryptedPassword = PasswordUtil.encryptPassword(admin.getAdminPw());
        admin.setAdminPw(encryptedPassword);

        adminService.save(admin);

        return "redirect:/mgr/admins";
    }


    //수정화면
    @GetMapping("/admins/{adminSeq}/update")
    public String updateForm(@PathVariable("adminSeq") Long adminSeq, Model model, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }
        model.addAttribute("post", adminService.find(adminSeq));
        model.addAttribute("title", "관리자관라-수정" );

        return "mgr/admin/update";
    }

    //수정
    @PostMapping("/admins/{adminSeq}")
    public String  update(@ModelAttribute Admin admin, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }
        adminService.update(admin);

        redirectAttributes.addAttribute("adminSeq", admin.getAdminSeq());
        redirectAttributes.addFlashAttribute("msg", "수정에 성공하였습니다.");

        return "redirect:/mgr/admins/{adminSeq}";

    }

    //사용여부 변경
    @GetMapping("/admins/{adminSeq}/useyn")
    public String  updateUseYN(@PathVariable("adminSeq") Long adminSeq, Model model, RedirectAttributes redirectAttributes, HttpSession session) {

        var adminSession = session.getAttribute("admin");

        if (adminSession == null) {
            // 세션에 사용자 정보가 없으면 로그인 화면으로 리다이렉트
            return "redirect:/login";
        }
        Admin admin = adminService.find(adminSeq);

        if (admin.getUseYn().equals("Y")) {
            admin.setUseYn("N");
        } else {
            admin.setUseYn("Y");
        }

        adminService.updateUseYn(admin);

        redirectAttributes.addAttribute("adminSeq", admin.getAdminSeq());
        redirectAttributes.addFlashAttribute("msg", "사용여부 수정에 성공하였습니다.");

        return "redirect:/mgr/admins";

    }

}
