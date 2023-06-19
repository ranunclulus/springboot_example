package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
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
    // 메소드 하나가 하나의 기능을 담당하는 원칙에 벗어나서 선호되지 않음
    @RequestMapping(
            value = "/path",
            method = { RequestMethod.PUT, RequestMethod.DELETE }
    )
    public String putOrDeletePath() {
        log.info("PUT or DELETE /path");
        return "index";
    }
}