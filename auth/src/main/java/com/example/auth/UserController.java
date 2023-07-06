package com.example.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    // 1. login 페이지로 온다
    // 2. login 페이지에 아이디 비밀번호를 입력한다
    // 3. 성공하면 my-profile로 이동한다
    @GetMapping("/login")
    public String loginForn() {
        return "login-form";
    }

    @GetMapping("/my-profile")
    public String myProfile() {
        return "my-profile";
    }
}
