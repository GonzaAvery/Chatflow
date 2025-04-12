package com.example.chatflow.controller;

import com.example.chatflow.model.User;
import com.example.chatflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint para registrar un usuario
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.createUser(username, password);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Endpoint para autenticar un usuario
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
        Optional<User> user = userService.authenticateUser(username, password);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    // Endpoint para validar si un usuario es válido
    @GetMapping("/{id}/valid")
    public ResponseEntity<Boolean> isUserValid(@PathVariable Long id) {
        boolean isValid = userService.isUserValid(id);
        return ResponseEntity.ok(isValid);
    }

    // (Opcional) Endpoint para listar todos los usuarios (solo para pruebas)
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}