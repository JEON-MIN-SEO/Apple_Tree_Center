package Apple.Center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home"; // 메인 페이지 뷰를 반환합니다.
    }
}
