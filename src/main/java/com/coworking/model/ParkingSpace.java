package com.coworking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Модель парковочного места")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {
    @Id
    @Column(name = "parking_space_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор парковочного места", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID parkingSpaceId;
    
    @NotBlank(message = "Номер места не может быть пустым")
    @Pattern(regexp = "^[A-Z]\\d{1,3}$", message = "Номер места должен начинаться с буквы и содержать 1-3 цифры")
    @Column(name = "space_number", nullable = false, length = 10, unique = true)
    @Schema(description = "Номер парковочного места", example = "A123", required = true)
    private String spaceNumber;
    
    @NotNull(message = "Статус не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Schema(description = "Статус парковочного места", example = "AVAILABLE", required = true)
    private ParkingSpaceStatus status;
    
    @NotNull(message = "Цена не может быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    @Column(name = "price_per_day", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Цена за день использования", example = "500.00", required = true)
    private BigDecimal pricePerDay;
    
    @Schema(description = "Статусы парковочного места")
    public enum ParkingSpaceStatus {
        @Schema(description = "Доступно для бронирования")
        AVAILABLE,
        @Schema(description = "Занято")
        OCCUPIED,
        @Schema(description = "Зарезервировано")
        RESERVED
    }
} 