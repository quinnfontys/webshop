package com.qrdsn.fullstackbackend.controller;

import com.qrdsn.fullstackbackend.model.Message;
import com.qrdsn.fullstackbackend.model.Status;
import com.qrdsn.fullstackbackend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }
    @MessageMapping("/message") // /app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        if (message.getStatus() == Status.JOIN && message.getSenderName().equalsIgnoreCase("admin")) {
            return chatService.loggedInAsAdmin();
        } else if (message.getStatus() == Status.MESSAGE && message.getSenderName().equalsIgnoreCase("admin")) {
            return chatService.parseString(message);
        }
        return message;
    }
}
