package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Модель бронирования рабочего пространства")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор бронирования", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID bookingId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "workspace_id",
            referencedColumnName = "workspace_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_booking_workspace")
    )
    @Schema(description = "Рабочее пространство", required = true)
    private Workspace workspace;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_booking_user")
    )
    @Schema(description = "Пользователь, сделавший бронирование", required = true)
    private User user;

    @NotNull(message = "Время начала не может быть пустым")
    @Future(message = "Время начала должно быть в будущем")
    @Column(name = "start_time", nullable = false)
    @Schema(description = "Время начала бронирования", example = "2024-03-20T10:00:00", required = true)
    private LocalDateTime startTime;

    @NotNull(message = "Время окончания не может быть пустым")
    @Future(message = "Время окончания должно быть в будущем")
    @Column(name = "end_time", nullable = false)
    @Schema(description = "Время окончания бронирования", example = "2024-03-20T18:00:00", required = true)
    private LocalDateTime endTime;

    @NotNull(message = "Общая стоимость не может быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Общая стоимость должна быть больше 0")
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Общая стоимость бронирования", example = "800.00", required = true)
    private BigDecimal totalPrice;

    @NotNull(message = "Статус не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Schema(description = "Статус бронирования", example = "PENDING", required = true)
    private BookingStatus status;

    @Schema(description = "Статусы бронирования")
    public enum BookingStatus {
        @Schema(description = "Ожидает подтверждения")
        PENDING,
        @Schema(description = "Подтверждено")
        CONFIRMED,
        @Schema(description = "Отменено")
        CANCELED,
        @Schema(description = "Завершено")
        COMPLETED
    }
} 