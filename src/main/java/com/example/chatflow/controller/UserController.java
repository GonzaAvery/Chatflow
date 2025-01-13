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
    @PostMapping
    public ResponseEntity<User> createUser(@RequestParam String username) {
        User user = userService.createUser(username);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Endpoint para validar si un usuario es válido
    @GetMapping("/{id}/valid")
    public ResponseEntity<Boolean> isUserValid(@PathVariable Long id) {
        boolean isValid = userService.isUserValid(id);
        return ResponseEntity.ok(isValid);
    }

    // (Opcional) Endpoint para listar todos los usuarios (solo para pruebas)
    /*@GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }*/
}