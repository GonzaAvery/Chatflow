package com.example.chatflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.chatflow.model.ChatMessage;
import com.example.chatflow.model.ChatMessageModel;
import com.example.chatflow.repository.WebSocketRepository;
import com.example.chatflow.service.MessageService;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "http://localhost:5174" }, allowCredentials = "true")
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final WebSocketRepository webSocketRepository;

    @Autowired
    public WebSocketController(SimpMessagingTemplate messagingTemplate,
            MessageService messageService,
            WebSocketRepository webSocketRepository) {
        this.messagingTemplate = messagingTemplate;
        this.messageService = messageService;
        this.webSocketRepository = webSocketRepository;
    }

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
        System.out.println("Mensaje recibido para la sala " + roomId + ": " + message);

        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setUser_name(message.getUser());
        chatMessageModel.setMessage(message.getMessage());
        chatMessageModel.setRoomId(roomId);
        webSocketRepository.save(chatMessageModel);

        messageService.updateLastMessageAt(roomId);

        return new ChatMessage(message.getMessage(), message.getUser());
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        ChatMessageModel chatMessageModel = new ChatMessageModel();
        chatMessageModel.setUser_name(message.getUser());
        chatMessageModel.setMessage(message.getMessage());
        webSocketRepository.save(chatMessageModel);
        return message;
    }

    @GetMapping("/api/chat/{roomId}")
    public ResponseEntity<List<ChatMessageModel>> getMessages(@PathVariable String roomId) {
        return ResponseEntity.ok(messageService.getMessages(roomId));
    }
}