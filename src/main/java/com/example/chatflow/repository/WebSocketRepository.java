package com.example.chatflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chatflow.model.ChatMessageModel;

@Repository
public interface WebSocketRepository extends JpaRepository<ChatMessageModel, Long> {
    List<ChatMessageModel> findByRoomId(String roomId);
}
