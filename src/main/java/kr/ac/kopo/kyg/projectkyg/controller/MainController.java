package kr.ac.kopo.kyg.projectkyg.controller;
import jakarta.servlet.http.HttpSession;
import kr.ac.kopo.kyg.projectkyg.domain.Member;
import kr.ac.kopo.kyg.projectkyg.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// Spring Security의 Authentication 객체를 사용하기 위해 SecurityContextHolder 추가
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MemberService memberService;

    @GetMapping({"/", "/main"})
    public String main(HttpSession session, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String memberId = authentication.getName();
        Member member = memberService.getMemberByMemberId(memberId);
        model.addAttribute("memberName", member.getMemberName());

        return "main";
    }
}