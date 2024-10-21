package com.project.chatflow.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.project.chatflow.entity.ChatRoom;
import com.project.chatflow.repository.ChatRoomRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ChatRoomServiceTest {
	
	@InjectMocks
    private ChatRoomService chatRoomService;

    @Mock
    private ChatRoomRepository chatRoomRepository;

    private ChatRoom chatRoom;

    @BeforeEach
    public void setUp() {
        chatRoom = new ChatRoom();
        chatRoom.setRoomCode(UUID.randomUUID().toString());
        chatRoom.setCreatedAt(LocalDateTime.now());
        chatRoom.setExpiresAt(LocalDateTime.now().plusHours(24));
    }
    
    @Test
    public void testCreateChatRoom() {
        // Configuramos el comportamiento del mock para el método save
        when(chatRoomRepository.save(any(ChatRoom.class))).thenAnswer(invocation -> {
            ChatRoom savedRoom = invocation.getArgument(0);
            return savedRoom; // Simula que se guarda y devuelve el mismo objeto
        });

        // Crear la sala de chat
        ChatRoom createdRoom = chatRoomService.createChatRoom();
        String generatedRoomCode = createdRoom.getRoomCode();

        // Verificar que la sala de chat no sea nula
        assertNotNull(createdRoom);
        
        // Verificar que el roomCode es el esperado
        assertEquals(generatedRoomCode, createdRoom.getRoomCode());
        
        // Verifica que el método save fue llamado una vez
        verify(chatRoomRepository, times(1)).save(any(ChatRoom.class));

        // Imprimir el ChatRoom creado
        System.out.println(createdRoom);
    }

    @Test
    public void testFindByRoomCode() {
      
    }

    @Test
    public void testDeleteExpiredChatRooms() {
        chatRoomService.deleteExpiredChatRooms();
        verify(chatRoomRepository, times(1)).deleteByExpiresAtBefore(any(LocalDateTime.class));
    }
}