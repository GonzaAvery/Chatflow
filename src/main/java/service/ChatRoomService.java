package service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.ChatRoom;
import repository.ChatRoomRepository;

@Service
public class ChatRoomService {
	
	@Autowired
    private ChatRoomRepository chatRoomRepository;

	// Crear una nueva sala de chat
    public ChatRoom createChatRoom() {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomCode(generateRoomCode());        
        chatRoom.setCreatedAt(LocalDateTime.now());
        chatRoom.setExpiresAt(LocalDateTime.now().plusHours(24));
        return chatRoomRepository.save(chatRoom);
    }

    // Buscar una sala de chat por su código único
    public Optional<ChatRoom> findByRoomCode(String roomCode) {
        return chatRoomRepository.findByRoomCode(roomCode);
    }

    // Eliminar salas de chat que hayan expirado
    public void deleteExpiredChatRooms() {
        chatRoomRepository.deleteByExpiresAtBefore(LocalDateTime.now());
    }

    // Obtener todas las salas de chat
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }
    
    private String generateRoomCode() {
        // Generar un código aleatorio de 32 o 64 caracteres, según tus necesidades
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);  // Genera un código de 32 caracteres
    }
}
