package com.coworking.service;

import com.coworking.model.User;

public interface AuthService {
    String login(String email, String password);
    User register(User user);
    void logout(String token);
    String refreshToken(String token);
} 