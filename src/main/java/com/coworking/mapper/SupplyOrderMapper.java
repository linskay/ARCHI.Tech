package com.coworking.mapper;

import com.coworking.dto.SupplyOrderDTO;
import com.coworking.exception.UserNotFoundException;
import com.coworking.model.SupplyOrder;
import com.coworking.model.User;
import com.coworking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SupplyOrderMapper {
    UUID orderId;
    UUID userId;
    List<String> items;
    LocalDateTime orderDate;
    SupplyOrder.SupplyOrderStatus status;

    private final UserRepository userRepository;

    public SupplyOrderDTO toDTO(SupplyOrder supplyOrder) {
        if (supplyOrder == null) {
            return null;
        }
        SupplyOrderDTO supplyOrderDTO = new SupplyOrderDTO();
        supplyOrderDTO.setOrderId(supplyOrder.getOrderId());
        supplyOrderDTO.setItems(supplyOrder.getItems());
        supplyOrderDTO.setUserId(supplyOrder.getUser().getUserId());
        supplyOrderDTO.setOrderDate(supplyOrder.getOrderDate());
        supplyOrderDTO.setStatus(supplyOrder.getStatus());

        return supplyOrderDTO;
    }

    public SupplyOrder toEntity (SupplyOrderDTO supplyOrderDTO) {
        if(supplyOrderDTO == null) {
            return null;
        }
        SupplyOrder supplyOrder = new SupplyOrder();
        supplyOrder.setOrderId(supplyOrderDTO.getOrderId());

        User user = userRepository.findById(supplyOrderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(supplyOrderDTO.getUserId()));

        supplyOrder.setUser(user);
        supplyOrder.setItems(supplyOrderDTO.getItems());
        supplyOrder.setOrderDate(supplyOrderDTO.getOrderDate());
        supplyOrder.setStatus(supplyOrderDTO.getStatus());
        return supplyOrder;
    }
}
