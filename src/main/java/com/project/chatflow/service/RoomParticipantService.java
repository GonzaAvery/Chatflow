package com.project.chatflow.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.chatflow.entity.ChatRoom;
import com.project.chatflow.entity.RoomParticipant;
import com.project.chatflow.repository.ChatRoomRepository;
import com.project.chatflow.repository.RoomParticipantRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomParticipantService {
	
	@Autowired
    private RoomParticipantRepository participantRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public RoomParticipant addParticipant(String roomCode, String participantName) {
        Optional<ChatRoom> chatRoomOpt = chatRoomRepository.findByRoomCode(roomCode);
        
        if (chatRoomOpt.isPresent()) {
            ChatRoom chatRoom = chatRoomOpt.get();
            RoomParticipant participant = new RoomParticipant();
            participant.setChatRoom(chatRoom);
            participant.setParticipantName(participantName);
            participant.setJoinedAt(LocalDateTime.now());
            
            return participantRepository.save(participant);
        } else {
            throw new EntityNotFoundException("Chat room not found");
        }
    }
}