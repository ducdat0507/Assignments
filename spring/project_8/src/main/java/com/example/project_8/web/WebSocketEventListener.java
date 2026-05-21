package com.example.project_8.web;

import com.example.project_8.model.ChatMessage;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    private final AppUserRegistry userRegistry;

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate, AppUserRegistry userRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.userRegistry = userRegistry;
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String username = headerAccessor.getSessionAttributes() == null ? null : (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            userRegistry.unregister(sessionId);
            ChatMessage leaveMessage = new ChatMessage();
            leaveMessage.setType(ChatMessage.MessageType.LEAVE);
            leaveMessage.setSender(username);
            messagingTemplate.convertAndSend("/topic/public", leaveMessage);
            messagingTemplate.convertAndSend("/topic/users", userRegistry.usernames());
        }
    }

}
