package com.project.chatflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.chatflow.entity.RoomParticipant;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant, Long> {

}
