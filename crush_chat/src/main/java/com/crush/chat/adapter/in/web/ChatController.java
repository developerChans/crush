package com.crush.chat.adapter.in.web;

import com.crush.chat.application.service.ChatService;
import com.crush.chat.domain.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/messages")
    public Chat createMessage(@RequestBody RequestCreateChat command) {
        return chatService.createMessage(command);
    }

    @GetMapping("/messages")
    public List<Chat> getAllMessages() {
        return chatService.getAllMessages();
    }
}
