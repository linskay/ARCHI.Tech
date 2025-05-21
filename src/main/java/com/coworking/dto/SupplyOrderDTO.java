package com.coworking.dto;

import com.coworking.model.SupplyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplyOrderDTO {
    @NotNull(message = "Идентификатор заказа обязателен")
    private UUID orderId;

    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID userId;

    @NotNull(message = "Список товаров обязателен")
    @Size(min = 1, message = "Список товаров не может быть пустым")
    private List<@NotBlank(message = "Наименование товара не может быть пустым") String> items;

    @NotNull(message = "Дата заказа обязательна")
    @PastOrPresent(message = "Дата заказа не может быть в будущем")
    private LocalDateTime orderDate;

    @NotNull(message = "Статус заказа обязателен")
    private SupplyOrder.SupplyOrderStatus status;
}
