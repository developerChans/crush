package com.crush.chat.application.service;

import com.crush.chat.adapter.in.web.RequestCreateChat;
import com.crush.chat.adapter.out.persistence.ChatPersistenceAdapter;
import com.crush.chat.domain.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatPersistenceAdapter chatPersistenceAdapter;

    public Chat createMessage(RequestCreateChat command) {
        Chat chat = new Chat();
        chat.setMatchId(command.getMatchId());
        chat.setMessage(command.getMessage());
        chat.setSentAt(new Timestamp(System.currentTimeMillis()));
        return chatPersistenceAdapter.save(chat);
    }

    public List<Chat> getAllMessages() {
        return chatPersistenceAdapter.findAll();
    }
}
