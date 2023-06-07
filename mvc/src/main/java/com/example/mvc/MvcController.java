package com.example.mvc;

import com.example.mvc.model.Student;
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
    @RequestMapping("/student")
    public String student(Model model) {
        model.addAttribute(
                "object",
                new Student("Huisu", "morion002@gmail.com"));
        return "student";
    }
    @RequestMapping("/is-logged-in")
    public String isLoggedIn(Model model) {
        model.addAttribute(
                "isLoggedIn",
                true
        );
        return "if-unless";
    }
}
