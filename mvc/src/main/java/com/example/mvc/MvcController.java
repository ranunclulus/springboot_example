package com.example.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {
    @RequestMapping("/")
    // view가 활용을 하기 위한 모델
    public String home(Model model) {
        model.addAttribute("message", "hello, thymeleaf");
        return "home";
    }

}
