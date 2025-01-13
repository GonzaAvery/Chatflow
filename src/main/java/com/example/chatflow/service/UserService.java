package com.example.chatflow.service;

import com.example.chatflow.model.User;
import com.example.chatflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Crear un nuevo usuario
    public User createUser(String username) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(72);
        User user = new User(username, now, expiresAt);
        return userRepository.save(user);
    }

    // Buscar un usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Verificar si un usuario es válido (no ha expirado)
    public boolean isUserValid(Long id) {
        return userRepository.findById(id)
                .map(user -> user.getExpiresAt().isAfter(LocalDateTime.now()))
                .orElse(false);
    }

    // Eliminar usuarios expirados
    public void deleteExpiredUsers() {
        List<User> expiredUsers = userRepository.findByExpiresAtBefore(LocalDateTime.now());
        userRepository.deleteAll(expiredUsers);
    }
}
