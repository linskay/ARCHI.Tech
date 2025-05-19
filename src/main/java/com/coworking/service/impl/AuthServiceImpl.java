package com.coworking.service.impl;

import com.coworking.model.User;
import com.coworking.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String login(String email, String password) {
        // TODO: Реализовать аутентификацию пользователя
        return "dummy-jwt-token";
    }

    @Override
    public User register(User user) {
        // TODO: Реализовать регистрацию нового пользователя
        return user;
    }

    @Override
    public void logout(String token) {
        // TODO: Реализовать выход из системы
    }

    @Override
    public String refreshToken(String token) {
        // TODO: Реализовать обновление токена
        return "new-dummy-jwt-token";
    }
} 