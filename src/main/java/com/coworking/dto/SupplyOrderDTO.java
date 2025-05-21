package com.coworking.dto;

import com.coworking.model.SupplyOrder;
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
    private UUID orderId;

    private UUID userId;

    private List<String> items;

    private LocalDateTime orderDate;

    private SupplyOrder.SupplyOrderStatus status;
}
