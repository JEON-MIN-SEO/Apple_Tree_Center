package Apple.Center.controller;

import Apple.Center.service.ElderlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin/elderly")
public class AdminController {

    @Autowired
    private ElderlyService elderlyService;

}
