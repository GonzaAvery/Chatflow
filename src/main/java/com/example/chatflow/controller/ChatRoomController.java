package com.example.chatflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.chatflow.model.ChatRoom;
import com.example.chatflow.service.ChatRoomService;

import java.util.List;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;
    
    public ChatRoomController(ChatRoomService chatRoomService) {
    	this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom() {
        // Llamar al servicio para crear una nueva sala de chat
        ChatRoom newChatRoom = chatRoomService.createRoom();
        return ResponseEntity.ok(newChatRoom); // Devolver la sala creada como respuesta
    }

    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        return ResponseEntity.ok(chatRoomService.getAllChatRooms());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id) {
        return chatRoomService.getChatRoomById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/code/{roomCode}")
    public ResponseEntity<ChatRoom> getChatRoomByRoomCode(@PathVariable String roomCode) {
        return chatRoomService.getChatRoomByRoomCode(roomCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatRoomById(@PathVariable Long id) {
        chatRoomService.deleteChatRoomById(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/code/{roomCode}")
    public ResponseEntity<Void> deleteChatRoomByRoomCode(@PathVariable String roomCode) {
        chatRoomService.deleteChatRoomByRoomCode(roomCode);
        return ResponseEntity.noContent().build();
    }
}