package com.example.project_8.web;

import com.example.project_8.model.ChatMessage;
import com.example.project_8.entity.MessageEntity;
import com.example.project_8.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;

    private final MessageService messageService;

    private final UserRegistry userRegistry;

    public ChatMessageController(SimpMessagingTemplate messagingTemplate, MessageService messageService, UserRegistry userRegistry) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.userRegistry = userRegistry;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        if (chatMessage.getType() == ChatMessage.MessageType.PRIVATE && chatMessage.getRecipient() != null) {
            messageService.save(chatMessage);
            messagingTemplate.convertAndSendToUser(chatMessage.getRecipient(), "/queue/private", chatMessage);
            messagingTemplate.convertAndSendToUser(chatMessage.getSender(), "/queue/private", chatMessage);
            return;
        }
        messageService.save(chatMessage);
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
    }

    @MessageMapping("/chat.addUser")
    public void addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor.getSessionId();
        userRegistry.register(sessionId, chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        chatMessage.setType(ChatMessage.MessageType.JOIN);
        messagingTemplate.convertAndSend("/topic/public", chatMessage);
        messagingTemplate.convertAndSend("/topic/users", userRegistry.usernames());
    }

    @MessageMapping("/chat.typing")
    public void typing(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend("/topic/typing", chatMessage);
    }

}
