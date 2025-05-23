package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Schema(description = "Модель заказа поставки")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supply_orders")
public class SupplyOrder {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор заказа", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID orderId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_supply_order_user")
    )
    @Schema(description = "Пользователь, создавший заказ", required = true)
    private User user;

    @JsonIgnore
    @ElementCollection
    @NotEmpty(message = "Список товаров не может быть пустым")
    @Size(min = 1, max = 50, message = "Количество товаров должно быть от 1 до 50")
    @Column(name = "items", nullable = false, columnDefinition = "TEXT")
    @Schema(description = "Список заказанных товаров", example = "[\"Кофе\", \"Печенье\", \"Вода\"]", required = true)
    private List<String> items;
    
    @NotNull(message = "Дата заказа не может быть пустой")
    @Column(name = "order_date", nullable = false)
    @Schema(description = "Дата и время создания заказа", example = "2024-03-20T10:00:00", required = true)
    private LocalDateTime orderDate;
    
    @NotNull(message = "Статус не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Schema(description = "Статус заказа", example = "PENDING", required = true)
    private SupplyOrderStatus status;
    
    @Schema(description = "Статусы заказа поставки")
    public enum SupplyOrderStatus {
        @Schema(description = "Ожидает обработки")
        PENDING,
        @Schema(description = "В обработке")
        PROCESSING,
        @Schema(description = "Отправлен")
        SHIPPED,
        @Schema(description = "Доставлен")
        DELIVERED,
        @Schema(description = "Отменен")
        CANCELED
    }
} 