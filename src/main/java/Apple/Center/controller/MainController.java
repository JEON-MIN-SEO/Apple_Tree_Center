package Apple.Center.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        return "home"; // 메인 페이지 뷰를 반환합니다.
    }
}
