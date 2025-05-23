package com.coworking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Модель рабочего пространства")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Идентификатор рабочего пространства", example = "550e8400-e29b-41d4-a716-446655440")
    @Column(name = "workspace_id")
    private UUID workspaceId;
    
    @NotBlank(message = "Название не может быть пустым")
    @Size(min = 3, max = 100, message = "Название должно содержать от 3 до 100 символов")
    @Schema(description = "Название рабочего пространства", example = "Мое рабочее пространство", required = true)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Size(max = 1000, message = "Описание не должно превышать 1000 символов")
    @Schema(description = "Описание рабочего пространства", example = "Описание рабочего пространства")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @NotNull(message = "Цена не может быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена должна быть больше 0")
    @Schema(description = "Цена за час использования", example = "100.00", required = true)
    @Column(name = "price_per_hour", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerHour;
    
    @NotNull(message = "Статус не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Schema(description = "Статус рабочего пространства", example = "AVAILABLE", required = true)
    private WorkspaceStatus status;
    
    @Schema(description = "Статусы рабочего пространства")
    public enum WorkspaceStatus {
        @Schema(description = "Доступно для бронирования")
        AVAILABLE,
        @Schema(description = "Занято")
        OCCUPIED,
        @Schema(description = "На обслуживании")
        UNDER_MAINTENANCE
    }
} 