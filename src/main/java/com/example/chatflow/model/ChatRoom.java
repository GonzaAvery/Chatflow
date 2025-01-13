package com.example.chatflow.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "room_code", unique = true, nullable = false)
	private String roomCode;
	
	@Column(name = "room_name")
	private String roomName;

    @Column(name = "created_at")
	private LocalDateTime createdAt;

    @Column(name = "expires_at")
	private LocalDateTime expiresAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}

	@Override
	public String toString() {
		return "ChatRoom [id=" + id + ", roomCode=" + roomCode + ", createdAt=" + createdAt + ", expiresAt=" + expiresAt
				+ "]";
	}
}