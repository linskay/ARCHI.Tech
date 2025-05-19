package com.coworking.service;

import com.coworking.model.User;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(UUID userId);
    User createUser(User user);
    User updateUser(UUID userId, User user);
    void deleteUser(UUID userId);
} 