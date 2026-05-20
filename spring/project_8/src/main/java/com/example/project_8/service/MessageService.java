package com.example.project_8.service;

import com.example.project_8.entity.MessageEntity;
import com.example.project_8.model.ChatMessage;
import com.example.project_8.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public MessageEntity save(ChatMessage msg) {
        MessageEntity e = new MessageEntity();
        e.setSender(msg.getSender());
        e.setRecipient(msg.getRecipient());
        e.setContent(msg.getContent());
        e.setType(msg.getType() == null ? "CHAT" : msg.getType().name());
        e.setTimestamp(msg.getTimestamp());
        return repository.save(e);
    }

    public List<MessageEntity> last50() {
        List<MessageEntity> list = repository.findTop50ByOrderByTimestampDesc();
        Collections.reverse(list);
        return list.stream().collect(Collectors.toList());
    }

}
