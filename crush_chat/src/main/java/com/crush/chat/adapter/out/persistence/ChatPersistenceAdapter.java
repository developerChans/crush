package com.crush.chat.adapter.out.persistence;

import com.crush.chat.domain.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChatPersistenceAdapter {

    @Autowired
    private ChatRepository chatRepository;

    public Chat save(Chat chat) {
        ChatEntity entity = new ChatEntity();
        entity.setMatchId(chat.getMatchId());
        entity.setMessage(chat.getMessage());
        entity.setSentAt(chat.getSentAt());
        entity = chatRepository.save(entity);
        chat.setId(entity.getId());
        return chat;
    }

    public List<Chat> findAll() {
        return chatRepository.findAll().stream().map(entity -> {
            Chat chat = new Chat();
            chat.setId(entity.getId());
            chat.setMatchId(entity.getMatchId());
            chat.setMessage(entity.getMessage());
            chat.setSentAt(entity.getSentAt());
            return chat;
        }).collect(Collectors.toList());
    }
}
