package com.project.chatflow.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.chatflow.entity.ChatRoom;
import com.project.chatflow.service.ChatRoomService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChatRoomControllerTest {

    @InjectMocks
    private ChatRoomController chatRoomController;

    @Mock
    private ChatRoomService chatRoomService;

    private ChatRoom chatRoom;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(chatRoomController).build();
    }

    @Test
    public void testCreateChatRoom() throws Exception {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomCode("testRoomCode");

        when(chatRoomService.createChatRoom()).thenReturn(chatRoom);

        mockMvc.perform(post("/chatrooms")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roomCode").value("testRoomCode"));
    }

    @Test
    public void testGetChatRoom() throws Exception {
        when(chatRoomService.findByRoomCode("testRoomCode")).thenReturn(Optional.of(chatRoom));

        mockMvc.perform(get("/chatrooms/testRoomCode")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomCode").value("testRoomCode"));
    }

    @Test
    public void testGetChatRoomNotFound() throws Exception {
        when(chatRoomService.findByRoomCode("testRoomCode")).thenReturn(Optional.empty());

        mockMvc.perform(get("/chatrooms/testRoomCode")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteExpiredChatRooms() throws Exception {
        mockMvc.perform(delete("/chatrooms/expired"))
                .andExpect(status().isNoContent());

        verify(chatRoomService, times(1)).deleteExpiredChatRooms();
    }
}