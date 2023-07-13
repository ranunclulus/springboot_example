package com.example.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SimpleChatHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions
            = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(
            WebSocketSession session
    ) throws Exception {
        // 방금 참여한 사용자를 저장
        sessions.add(session);
        log.info("connected with session id: {}, total sessions: {}", session.getId(), sessions.size());
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("recieved: {}", payload);
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status) throws Exception {
        log.info("connection with {} closed", session.getId());
        // 더 이상 세션 객첼ㄹ 보유하지 않도록 함
        sessions.remove(session);
    }

}
