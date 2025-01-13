package com.example.chatflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.chatflow.model.Message;
import com.example.chatflow.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {
	
//	private final MessageService messageService;
//	private final SimpMessagingTemplate messagingTemplate;
//	
//	@Autowired
//	public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
//		this.messageService = messageService;
//		this.messagingTemplate = messagingTemplate;
//	}
//	
//	@PostMapping("/chatroom/{roomCode}")
//    public ResponseEntity<Message> sendMessage(@PathVariable String roomCode, @RequestBody Message message) {
//        Message savedMessage = messageService.sendMessage(roomCode, message);
//        messagingTemplate.convertAndSend("/topic/" + roomCode, savedMessage);
//        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
//    }
//	
//	@GetMapping("/chatroom/{roomCode}")
//    public ResponseEntity<List<Message>> getMessages(@PathVariable String roomCode) {
//        List<Message> messages = messageService.getMessages(roomCode);
//        return new ResponseEntity<>(messages, HttpStatus.OK);
//    }
//	
//	@MessageMapping("/chatroom/{roomCode}")
//    @SendTo("/topic/{roomCode}")
//    public Message handleWebSocketMessage(@PathVariable String roomCode, Message message) {
//        return messageService.sendMessage(roomCode, message);
//    }
}
