package com.example.chatflow.repository;

import com.example.chatflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar usuarios que hayan expirado
    List<User> findByExpiresAtBefore(LocalDateTime now);

    // Buscar usuario por nombre (opcional, si lo necesitas)
    List<User> findByUsername(String username);
}