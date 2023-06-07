package com.example.mvc;

import com.example.mvc.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MvcController {
    private int hitCount = 0;
    private List<List<Integer>> history = new ArrayList<>();
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
    @RequestMapping("/each")
    public String items(Model model) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("foo");
        listOfStrings.add("bar");
        listOfStrings.add("baz");
        model.addAttribute("listOfStrings", listOfStrings);

        List<Student> studentList = Arrays.asList( // add 대신에 쓰는 것
                new Student("Alex", "alex@gmail.com"),
                new Student("Brad", "brad@gmail.com"),
                new Student("Chad", "chad@gmail.com")
        );
        model.addAttribute("studentList", studentList);
        return "each";
    }

    @RequestMapping("/hits")
    public String hits(Model model) {
        model.addAttribute("hits", ++hitCount);
        return "hits";
    }

    @RequestMapping("/lotto")
    public String lotto(Model model) {
        List<Integer> lottoNum = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int num = (int)(Math.random() * 45) + 1;
            lottoNum.add(num);
        }
        history.add(lottoNum);
        model.addAttribute("lotto", lottoNum.toString());
        return "lotto";
    }

    @RequestMapping("history")
    public String history(Model model) {
        return "history";
    }
}
