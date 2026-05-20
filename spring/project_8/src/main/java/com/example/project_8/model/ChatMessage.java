package com.example.project_8.model;

import java.time.Instant;

public class ChatMessage {

    public enum MessageType {CHAT, JOIN, LEAVE, TYPING, STOP_TYPING, PRIVATE}

    private MessageType type;

    private String sender;

    private String content;

    private String recipient;

    private Instant timestamp = Instant.now();

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
