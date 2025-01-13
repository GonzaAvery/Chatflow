package com.example.chatflow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chatflow.model.ChatRoom;
import com.example.chatflow.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByChatRoom(ChatRoom chatRoom);
}
