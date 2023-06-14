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

    @GetMapping("create")
    public @ResponseBody String create() {
        this.service.createStudent(
                "alex",
                35,
                "010-1234-5678",
                "alex@gmail.com"
        );
        return "done";
    }

    @GetMapping("read-all")
    public @ResponseBody String readAll() {
        this.service.readStudentAll();
        return "done-read-all";
    }

    @GetMapping("read")
    public @ResponseBody String readOne() {
        this.service.readStudent(1L);
        return "done-read-one";
    }
/*
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

 */
}
