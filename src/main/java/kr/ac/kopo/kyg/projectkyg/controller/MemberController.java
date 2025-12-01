package kr.ac.kopo.kyg.projectkyg.controller;

import jakarta.validation.Valid;
import kr.ac.kopo.kyg.projectkyg.domain.Member;
import kr.ac.kopo.kyg.projectkyg.domain.MemberFormDto;
import kr.ac.kopo.kyg.projectkyg.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    // 회원가입 로직 처리를 위해 MemberService와 PasswordEncoder 주입
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 1. 회원가입 페이지(signup.html)를 보여주는 GET 요청 매핑.
     * URL: /members/new
     */
    @GetMapping("/members/new")
    public String signup(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "signup"; // templates/signup.html 반환
    }

    /**
     * 2. 회원가입 정보를 처리하는 POST 요청 매핑.
     * URL: /members/new
     */
    @PostMapping("/members/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult,
                             Model model) {

        // 유효성 검사 오류가 있을 경우, 다시 회원가입 폼으로 돌아감
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        try {
            // DTO를 Entity로 변환 및 비밀번호 암호화 후 저장
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e) {
            // 중복 회원 예외 발생 시 에러 메시지를 모델에 담아 다시 폼으로 돌아감
            model.addAttribute("error", e.getMessage());
            return "signup";
        }

        // 회원가입 성공 시 로그인 페이지로 리다이렉트 (SecurityConfig 설정 경로와 일치)
        return "redirect:/members/login";
    }
}