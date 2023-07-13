package com.example.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@Slf4j
@Controller
public class HeaderController {
    // 헤더를 통해 추가적인 정보 전달이 필요할 때 ex 인증 정보

    // header-one으로 들어온 HTTP 요청에 대하여
    // 헤더 중 x-likelion이라는 헤더의 값을 인자로 전달받고 싶을 때
    @PostMapping("/header-one") // 한 개의 헤더를 대할 때
    public String getHeader(
            @RequestHeader("x-likelion") String header
            ) {
        log.info("POST /header-one header: " + header);
        return "index";
    }

    @PostMapping("/header-optional")
    public String getHeaderOptional(
            @RequestHeader(value = "x-likelion", required = false) String header
    ) {
        log.info("POST /header-optional header: " + header);
        return "index";
    }

    @PostMapping("/header-type")
    public String getHeaderInteger(
            @RequestHeader(value = "x-likelion-int") Integer header
    ) {
        log.info("POST /header-type header: " + header);
        return "index";
    }

    // "header-all"로 들어온 HTTP 요청의 모든 헤더를 확인하고 싶을 때
    @PostMapping("header-all")
    public String headerAll(
            @RequestHeader // 모든 헤더를 다 가져올 때는 인자를 정의하지 않으면 됨
            Map<String, String> headerMap
    ) {
        log.info("POST /header-all");
        for(Map.Entry<String, String> entry : headerMap.entrySet()) {
            log.info(String.format("%s: %s", entry.getKey(), entry.getValue()));
        }
        return "index";
    }


}
