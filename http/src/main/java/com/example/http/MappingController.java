package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j  // log 변수 만들어줌
public class MappingController {
//    private static final Logger logger = LoggerFactory.getLogger(MappingController.class);

    // '/path'로 오는 GET 요청에 대해서 메소드를 실행하고 싶을 때
    @RequestMapping(value = "/path", method = RequestMethod.GET)
    public String getPath() {
        log.info("GET /path");
        return "index";
    }

    // '/path'로 오는 POST 요청에 대해서 메소드를 실행하고 싶을 때
    @RequestMapping(value = "/path", method = RequestMethod.POST)
    public String postPath() {
        log.info("POST /path");
        return "index";
    }

    // '/path'로 오는 PUT 또는 DELETE 요청에 대해서
    // 메소드를 실행하고 싶을 때
    @RequestMapping(value = "/path",
            method = { RequestMethod.PUT, RequestMethod.DELETE })
    public String putOrDeletePath() {
        log.info("PUT or DELETE /path");
        return "index";
    }

    // '/path'로 오는 POST 요청이면서
    // 헤더에 x-likelion이라는 값이 hello로 지정되어 있을 때
    @RequestMapping(
            value = "/path",
            method = RequestMethod.POST,
            headers = "x-likelion=hello"
    )
    public String headerWith() {
        log.info("POST /path with x-likelion=hello");
        return "index";
    }
}