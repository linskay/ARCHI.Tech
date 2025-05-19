package com.coworking.service.impl;

import com.coworking.model.User;
import com.coworking.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAllUsers() {
        // TODO: Реализовать получение списка всех пользователей из базы данных
        return List.of();
    }

    @Override
    public User getUserById(UUID userId) {
        // TODO: Реализовать получение пользователя по ID из базы данных
        return null;
    }

    @Override
    public User createUser(User user) {
        // TODO: Реализовать создание нового пользователя в базе данных
        return user;
    }

    @Override
    public User updateUser(UUID userId, User user) {
        // TODO: Реализовать обновление данных пользователя в базе данных
        return user;
    }

    @Override
    public void deleteUser(UUID userId) {
        // TODO: Реализовать удаление пользователя из базы данных
    }
} 