package com.example.validation;

import com.example.validation.dto.UserDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class UserController {
    @PostMapping("/users")
    public ResponseEntity<Map<String, String>> addUser(
            // UserDto가 우리가 정의한 요구 사항을
            // 지키고 있는지 유효성 검사
            @Valid
            @RequestBody UserDto dto
            ) {
        log.info(dto.toString());
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "success!");
        return ResponseEntity.ok(responseBody);
    }
}
