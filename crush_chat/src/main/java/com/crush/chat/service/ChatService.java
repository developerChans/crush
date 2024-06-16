package com.crush.chat.service;

import com.crush.chat.model.entity.ChatMessage;
import com.crush.chat.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    public List<ChatMessage> getAllMessages() {
        return chatRepository.findAll();
    }

    public ChatMessage sendMessage(ChatMessage message) {
        return chatRepository.save(message);
    }
}
