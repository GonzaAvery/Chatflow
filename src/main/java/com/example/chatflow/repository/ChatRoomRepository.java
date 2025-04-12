package com.example.chatflow.repository;

import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.chatflow.model.ChatRoom;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

	Optional<ChatRoom> findByRoomCode(String roomCode);

	void deleteByRoomCode(String roomCode);

	List<ChatRoom> findByExpiresAtBefore(LocalDateTime dateTime);

	List<ChatRoom> findAllByOrderByLastMessageAtDesc();
}
