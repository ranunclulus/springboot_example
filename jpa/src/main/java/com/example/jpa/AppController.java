package com.example.jpa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AppController {
    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    @RequestMapping("student")
    public void student() {
        List<Object> result = service.readStudentAll();
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @GetMapping("body")
    public @ResponseBody String body() {
        return "body";
    }
}
