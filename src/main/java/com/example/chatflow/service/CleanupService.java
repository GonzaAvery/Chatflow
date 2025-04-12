package com.example.chatflow.service;

import com.example.chatflow.model.User;
import com.example.chatflow.model.ChatRoom;
import com.example.chatflow.repository.UserRepository;
import com.example.chatflow.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CleanupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Scheduled(fixedRate = 3600000) // Ejecutar cada hora
    public void cleanupExpiredData() {
        LocalDateTime now = LocalDateTime.now();

        // Limpiar usuarios expirados (72 horas)
        List<User> expiredUsers = userRepository.findByExpiresAtBefore(now);
        userRepository.deleteAll(expiredUsers);

        // Limpiar salas expiradas (24 horas)
        List<ChatRoom> expiredRooms = chatRoomRepository.findByExpiresAtBefore(now);
        chatRoomRepository.deleteAll(expiredRooms);
    }
}