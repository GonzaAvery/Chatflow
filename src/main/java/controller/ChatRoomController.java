package controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.ChatRoom;
import service.ChatRoomService;

@RestController
@RequestMapping("/chatrooms")
public class ChatRoomController {

	private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // Endpoint para crear una nueva sala de chat
    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom() {
        ChatRoom chatRoom = chatRoomService.createChatRoom();
        return ResponseEntity.status(HttpStatus.CREATED).body(chatRoom);
    }

    // Endpoint para obtener una sala de chat por su código
    @GetMapping("/{roomCode}")
    public ResponseEntity<ChatRoom> getChatRoom(@PathVariable String roomCode) {
        Optional<ChatRoom> chatRoom = chatRoomService.findByRoomCode(roomCode);
        return chatRoom.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint para eliminar las salas de chat expiradas
    @DeleteMapping("/expired")
    public ResponseEntity<Void> deleteExpiredChatRooms() {
        chatRoomService.deleteExpiredChatRooms();
        return ResponseEntity.noContent().build();
    }
}
