package com.example.chat;

import com.example.chat.dto.ChatMessage;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
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

    public void broadcast(String message) throws IOException {
        for (WebSocketSession connected: sessions) {
            connected.sendMessage(new TextMessage(message));
        }
    }

    @Override
    protected void handleTextMessage(
            WebSocketSession session,
            TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessage chatMessage = new Gson().fromJson(payload, ChatMessage.class);
        log.info("recieved: {}", payload);
        for(WebSocketSession conneted: sessions) {
            conneted.sendMessage(message);
        }
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
