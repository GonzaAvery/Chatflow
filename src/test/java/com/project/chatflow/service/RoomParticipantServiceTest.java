package com.project.chatflow.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project.chatflow.entity.ChatRoom;
import com.project.chatflow.entity.RoomParticipant;
import com.project.chatflow.repository.ChatRoomRepository;
import com.project.chatflow.repository.RoomParticipantRepository;

public class RoomParticipantServiceTest {
	
	@Mock
    private ChatRoomRepository chatRoomRepository;

    @Mock
    private RoomParticipantRepository participantRepository;

    @InjectMocks
    private RoomParticipantService participantService;

    private ChatRoom chatRoom;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        chatRoom = new ChatRoom();
        chatRoom.setRoomCode("1234");
    }

    @Test
    public void testAddParticipant() {
        String roomCode = "1234";
        String participantName = "John Doe";

        // Configurar comportamiento de los mocks
        when(chatRoomRepository.findByRoomCode(roomCode)).thenReturn(Optional.of(chatRoom));
        when(participantRepository.save(any(RoomParticipant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Llamada al servicio
        RoomParticipant addedParticipant = participantService.addParticipant(roomCode, participantName);

        // Verificaciones
        assertNotNull(addedParticipant);
        assertEquals(roomCode, addedParticipant.getChatRoom().getRoomCode());
        assertEquals(participantName, addedParticipant.getParticipantName());
        verify(participantRepository, times(1)).save(any(RoomParticipant.class));
        
        System.out.println("Added Participant: " + addedParticipant);
    }
}