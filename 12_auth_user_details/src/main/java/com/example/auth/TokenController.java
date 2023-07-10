package com.example.auth;

import com.example.auth.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("token")
public class TokenController {
    private final JwtTokenUtils jwtTokenUtils;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;


    public TokenController(
            JwtTokenUtils jwtTokenUtils,
            UserDetailsManager userDetailsManager,
            PasswordEncoder passwordEncoder) {
        this.jwtTokenUtils = jwtTokenUtils;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    // JWT 발급을 받는 Mapping: PostMapping("/issue")
    // RequestBody: 인증받고자 하는 사용자가 ID 비밀번호를 전달 (JwtRequestDto)
    // ResponseBody: 발급이 완료된 JWT 전달 (JwtTokenDto)
}
