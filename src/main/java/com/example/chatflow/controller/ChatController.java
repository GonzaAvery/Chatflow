package com.example.chatflow.controller;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.chatflow.model.Message;
import com.example.chatflow.repository.MessageRepository;

@Controller
public class ChatController {

	private final MessageRepository messageRepository;
	
	public ChatController(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/messages")
	public Message sendMessage(Message message) {
		message.setSentAt(LocalDateTime.now());
		return messageRepository.save(message);
	}
}
