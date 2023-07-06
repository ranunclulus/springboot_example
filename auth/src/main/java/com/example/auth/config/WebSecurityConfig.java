package com.example.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {
        http.authorizeHttpRequests(
                authHttp -> authHttp
                        .requestMatchers("/no-auth")
                        .permitAll()
                        .requestMatchers("/re-auth")
                        .authenticated()
        ); // HTTP 요청 허가 관련 설정
        return http.build();
    }

}
