package com.example.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @RequestMapping("/send")
    public String getForm(){
        return "send";
    }

    @PostMapping("/receive")
    public String receive(
            @RequestParam("msg")
            String msg,
            @RequestParam("email")
            String email
    ) {
        System.out.println(msg);
        System.out.println(email);
        return "send";
    }
}
