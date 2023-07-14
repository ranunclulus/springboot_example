package com.example.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(
            // HttpServletRequest & Response 대신
            // exchange
            ServerWebExchange exchange,
            // FilterChain
            GatewayFilterChain chain) {
        // 사용자가 보낸 HTTP 요청을 받음
        ServerHttpRequest httpRequest = exchange.getRequest();
        // PreLoggingFilter에서 요청을 식별할 수 있는 HTTP Header를 작성
        // 나중에 PostLoggingFilter를 만들 때 해당 Header를 바탕으로
        // 실행에 걸린 시간 측정

        String requestId = UUID.randomUUID().toString();
        // 사용자가 보낸 요청을 커스텀
        httpRequest.mutate()
                // 헤더 추가 및 변형
                .headers(httpHeaders -> {
                    httpHeaders.add(
                            "x-gateway-request-id",
                            requestId
                    );
                    httpHeaders.add(
                            "x-gateway-request-time",
                            String.valueOf(Instant.now().toEpochMilli())
                    );
                });

        log.info("start transaction: {}", requestId);
        // filterChain.doFilter() 대신
        return chain.filter(exchange);
    }
}
