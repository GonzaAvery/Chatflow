package com.example.chatflow.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chatflow.model.ChatMessageModel;
import com.example.chatflow.model.ChatRoom;
import com.example.chatflow.repository.ChatRoomRepository;
import com.example.chatflow.repository.WebSocketRepository;

@Service
public class MessageService {

    private final WebSocketRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;

    @Autowired
    public MessageService(WebSocketRepository messageRepository, ChatRoomRepository chatRoomRepository,
            ChatRoomService chatRoomService) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.chatRoomService = chatRoomService;
    }

    public List<ChatMessageModel> getMessages(String roomId) {
        return messageRepository.findByRoomId(roomId);
    }

    public ChatMessageModel createMessage(String roomId, String senderName, String content) {
        ChatRoom chatRoom = chatRoomRepository.findByRoomCode(roomId)
                .orElseThrow(() -> new RuntimeException("Sala de chat no encontrada"));

        ChatMessageModel message = new ChatMessageModel();
        message.setRoomId(roomId);
        message.setUser_name(senderName);
        message.setMessage(content);

        return messageRepository.save(message);
    }

    public void updateLastMessageAt(String roomId) {
        chatRoomService.updateLastMessageAt(roomId);
    }
}