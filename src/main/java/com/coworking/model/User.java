package com.coworking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
@Schema(description = "Модель пользователя системы")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    @Schema(description = "Уникальный идентификатор пользователя", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID userId;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    @Column(name = "first_name", nullable = false, length = 50)
    @Schema(description = "Имя пользователя", example = "Иван", required = true)
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(min = 2, max = 50, message = "Фамилия должна содержать от 2 до 50 символов")
    @Column(name = "last_name", nullable = false, length = 50)
    @Schema(description = "Фамилия пользователя", example = "Иванов", required = true)
    private String lastName;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Column(name = "email", nullable = false, unique = true)
    @Schema(description = "Email пользователя", example = "ivan@example.com", required = true)
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 20, message = "Пароль должен содержать от 6 до 20 символов")
    @Column(name = "password", nullable = false, length = 20)
    @Schema(description = "Пароль пользователя", example = "password123", required = true)
    private String password;

    @NotBlank(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "^\\+?[1-9]\\d{10,14}$", message = "Некорректный формат номера телефона")
    @Column(name = "phone_number", nullable = false, length = 20)
    @Schema(description = "Номер телефона пользователя", example = "+79001234567", required = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 10)
    @Schema(description = "Роль пользователя в системе", example = "USER", required = true)
    private UserRole role;

    @Schema(description = "Роли пользователей в системе")
    public enum UserRole {
        @Schema(description = "Обычный пользователь")
        USER,
        @Schema(description = "Администратор системы")
        ADMIN
    }
} 