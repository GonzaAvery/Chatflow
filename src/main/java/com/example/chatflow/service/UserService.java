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

    public User createUser(String username, String password) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiresAt = now.plusHours(72);
        User user = new User(username, password, now, expiresAt);
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> authenticateUser(String username, String password) {
        return userRepository.findAll().stream()
                .filter(user -> user.getUsername().equals(username) &&
                        user.getPassword().equals(password) &&
                        user.getExpiresAt().isAfter(LocalDateTime.now()))
                .findFirst();
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
