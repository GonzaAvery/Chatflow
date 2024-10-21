package com.project.chatflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.chatflow.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
