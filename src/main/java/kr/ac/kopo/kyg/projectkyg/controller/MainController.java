package kr.ac.kopo.kyg.projectkyg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // "/"와 "/main.html" 모두 main 페이지 렌더링
    @GetMapping({"/", "/main"})
    public String main() {
        return "main";  // templates/main.html
    }
}
