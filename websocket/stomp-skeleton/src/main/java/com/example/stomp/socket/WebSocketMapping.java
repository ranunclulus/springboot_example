package com.example.stomp.socket;

import com.example.stomp.dto.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebSocketMapping {
    // STOMP over WebSocket 요청을 보낼 때 사용하는 용도
    // session에 메시지를 썼던 것처럼
    private final SimpMessagingTemplate simpMessagingTemplate;

    // RequestMapping 에 대응
    @MessageMapping("/chat")
    public void sendChat(ChatMessage chatMessage) {
        log.info(chatMessage.toString());
        String time = new SimpleDateFormat("HH:mm").format(new Date());
    }

}
