package com.coworking.service;

import com.coworking.dto.UserDTO;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(UUID userId);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UUID userId, UserDTO userDTO);
    void deleteUser(UUID userId);
} 