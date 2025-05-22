package com.coworking.dto;

import com.coworking.model.Booking;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    @NotNull(message = "Идентификатор бронирования обязателен")
    private UUID bookingId;

    @NotNull(message = "Идентификатор рабочего места обязателен")
    private UUID workspaceId;

    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID userId;

    @NotNull(message = "Дата и время начала обязательны")
    @FutureOrPresent(message = "Дата и время начала должны быть настоящими или будущими")
    private LocalDateTime startTime;

    @NotNull(message = "Дата и время окончания обязательны")
    @FutureOrPresent(message = "Дата и время окончания должны быть настоящими или будущими")
    private LocalDateTime endTime;

    @NotNull(message = "Общая стоимость обязательна")
    @DecimalMin(value = "0.0", inclusive = false, message = "Общая стоимость должна быть больше 0")
    private BigDecimal totalPrice;

    @NotNull(message = "Статус обязателен")
    private Booking.BookingStatus status;
}
