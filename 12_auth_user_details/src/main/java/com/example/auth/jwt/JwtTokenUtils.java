package com.example.auth.jwt;

import com.example.auth.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;

@Slf4j
@Component
// JWT 생성, 인증 등의 기능을 가지고 있을 컴포넌트
public class JwtTokenUtils {
    // JWT는 암호화를 거쳐서 만들어지는데
    // 이를 위해서 암호키가 필요
    private final Key signingKey;
    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String jwtSecret) {
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // 새로운 JWT를 발급하는 메소드
    public String generateToken(UserDetails userDetails) {
        // Claim: JWT에 담길 데이터의 키 (맵의 키와 비슷한 용도)
        // JWT에 담고 싶은 사용자 정보
        Claims jwtClaims = Jwts.claims()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(3600)));
        jwtClaims.put("eml", ((CustomUserDetails) userDetails).getEmail());
        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(signingKey)
                .compact();
    }
}
