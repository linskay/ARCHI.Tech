package com.coworking.controller;

import com.coworking.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // TODO: Implement
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        // TODO: Implement
        return ResponseEntity.ok(new User());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // TODO: Implement
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User user) {
        // TODO: Implement
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        // TODO: Implement
        return ResponseEntity.noContent().build();
    }
} 