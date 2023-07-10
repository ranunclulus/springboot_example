package com.example.auth.jwt;

import com.example.auth.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
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
    private final JwtParser jwtParser; // JWT 해석키
    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String jwtSecret) {
        this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.signingKey)
                .build();
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

    // 1. JWT가 유효한지 판단하는 메소드
    // jjwt 라이브러리에서는 JWT를 해석하는 과정에서
    // 유효하지 않으면 예외 발생
    public boolean validate(String token) {
        try {
            // 정당한 JWT면 true,
            // parseClaimsJws: 암호화된 JWT를 해석하기 위한 메소드
            jwtParser.parseClaimsJwt(token);
            // 정당하지 않은 JWT이면 false
            return true;
        } catch (Exception e) {
            log.warn("invalid jwt");
            return false;
        }
    }

    // JWT를 인자로 받고 그 JWT를 해석해서 사용자 정보를 회수하는 메소드
    public Claims parseClaims(String token) {
        return jwtParser.parseClaimsJwt(token).getBody();
    }
}
