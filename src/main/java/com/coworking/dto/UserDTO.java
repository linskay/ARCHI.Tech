package com.coworking.dto;

import com.coworking.model.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID userId;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 1, max = 50, message = "Имя должно содержать от 1 до 50 символов")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 1, max = 50, message = "Фамилия должна содержать от 1 до 50 символов")
    private String lastName;

    @NotBlank(message = "Электронная почта обязательна")
    @Email(message = "Неверный формат электронной почты")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 20, message = "Пароль должен содержать от 8 до 20 символов")
    private String password;

    @NotBlank(message = "Номер телефона обязателен")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Неверный формат номера телефона")
    private String phoneNumber;

    @NotNull(message = "Роль обязательна")
    private User.UserRole role;
}
