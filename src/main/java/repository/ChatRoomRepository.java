package repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{

	// Buscar una sala de chat por su código único
    Optional<ChatRoom> findByRoomCode(String roomCode);

    // Eliminar las salas de chat que hayan expirado según la fecha expiresAt
    void deleteByExpiresAtBefore(LocalDateTime now);
}
