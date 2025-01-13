package com.example.chatflow.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.chatflow.model.ChatRoom;
import com.example.chatflow.repository.ChatRoomRepository;

@Service
public class ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;
	
	public ChatRoomService(ChatRoomRepository chatRoomRepository) {
		this.chatRoomRepository = chatRoomRepository;
	}
	
	public ChatRoom createRoom() {
		ChatRoom newChatRoom = new ChatRoom();
		newChatRoom.setRoomCode(UUID.randomUUID().toString().substring(0, 8));
		LocalDateTime now = LocalDateTime.now();
		newChatRoom.setCreatedAt(now);
		newChatRoom.setExpiresAt(now.plusDays(1)); // Expiración en 24 horas
		return chatRoomRepository.save(newChatRoom);
	}

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }
    
    public Optional<ChatRoom> getChatRoomById(Long id) {
    	return chatRoomRepository.findById(id);
    }
    
	public Optional<ChatRoom> getChatRoomByRoomCode(String roomCode) {
		return chatRoomRepository.findByRoomCode(roomCode);
	}
    
    
    public void deleteChatRoomById(Long id) {
    	chatRoomRepository.deleteById(id);
    }
    
    public void deleteChatRoomByRoomCode(String roomCode) {
    	chatRoomRepository.deleteByRoomCode(roomCode);
    }
}
