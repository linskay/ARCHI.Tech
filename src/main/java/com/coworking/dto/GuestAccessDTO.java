package com.coworking.dto;

import com.coworking.model.GuestAccess;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestAccessDTO {
    @NotNull(message = "Идентификатор гостевого доступа обязателен")
    private UUID guestAccessId;

    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID userId;

    @NotBlank(message = "Имя гостя обязательно")
    @Size(min = 1, max = 100, message = "Имя гостя должно содержать от 1 до 100 символов")
    private String guestName;

    @NotBlank(message = "Электронная почта гостя обязательна")
    @Email(message = "Неверный формат электронной почты")
    private String guestEmail;

    @NotNull(message = "Дата и время начала доступа обязательны")
    private LocalDateTime accessStartTime;

    @NotNull(message = "Дата и время окончания доступа обязательны")
    private LocalDateTime accessEndTime;

    @NotNull(message = "Статус обязателен")
    private GuestAccess.GuestAccessStatus status;
}
