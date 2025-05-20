package com.coworking.service.impl;

import com.coworking.exception.SupplyOrderNotFoundException;
import com.coworking.model.SupplyOrder;
import com.coworking.model.User;
import com.coworking.repository.SupplyOrderRepository;
import com.coworking.service.SupplyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupplyOrderServiceImpl implements SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final UserRepository userRepository;

    @Override
    public List<SupplyOrder> getAllSupplyOrders() {
        return supplyOrderRepository.findAll();
    }

    @Override
    public SupplyOrder getSupplyOrderById(UUID orderId) {
        return supplyOrderRepository.findById(orderId)
                .orElseThrow(() -> new SupplyOrderNotFoundException(orderId));
    }

    @Override
    public SupplyOrder createSupplyOrder(SupplyOrder supplyOrder) {
        UUID userId = supplyOrder.getUser().getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        supplyOrder.setUser(user);
        supplyOrder.setOrderDate(LocalDateTime.now());
        supplyOrder.setStatus(SupplyOrder.SupplyOrderStatus.PENDING);
        return supplyOrderRepository.save(supplyOrder);
    }

    @Override
    public SupplyOrder updateSupplyOrder(UUID orderId, SupplyOrder supplyOrder) {
        SupplyOrder existingOrder = supplyOrderRepository.findById(orderId)
                .orElseThrow(() -> new SupplyOrderNotFoundException(orderId));

        existingOrder.setItems(supplyOrder.getItems());
        existingOrder.setStatus(supplyOrder.getStatus());

        if (supplyOrder.getUser() != null) {
            UUID userId = supplyOrder.getUser().getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            existingOrder.setUser(user);
        }

        return supplyOrderRepository.save(existingOrder);
    }

    @Override
    public void deleteSupplyOrder(UUID orderId) {
        if (!supplyOrderRepository.existsById(orderId)) {
            throw new SupplyOrderNotFoundException(orderId);
        }
        supplyOrderRepository.deleteById(orderId);
    }
} 