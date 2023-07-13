package com.example.chat.config;

import com.example.chat.SimpleChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    // TODO @Bean 객체로 SimpleChatHandler 가져오기
    private final SimpleChatHandler simpleChatHandler;

    public WebSocketConfig(SimpleChatHandler simpleChatHandler) {
        this.simpleChatHandler = simpleChatHandler;
    }

    @Override
    // WebSocketHadler 등록을 위한 메소드
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(simpleChatHandler, "ws/chat")
                .setAllowedOrigins("*");
    }
}
