package com.coworking.service.impl;

import com.coworking.dto.SupplyOrderDTO;
import com.coworking.exception.SupplyOrderNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.mapper.SupplyOrderMapper;
import com.coworking.model.SupplyOrder;
import com.coworking.model.User;
import com.coworking.repository.SupplyOrderRepository;
import com.coworking.service.SupplyOrderService;
import com.coworking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplyOrderServiceImpl implements SupplyOrderService {

    private final SupplyOrderRepository supplyOrderRepository;
    private final UserRepository userRepository;
    private final SupplyOrderMapper supplyOrderMapper;

    @Override
    public List<SupplyOrderDTO> getAllSupplyOrders() {
        List<SupplyOrder> orders = supplyOrderRepository.findAll();
        return orders.stream()
                .map(supplyOrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplyOrderDTO getSupplyOrderById(UUID orderId) {
        SupplyOrder order = supplyOrderRepository.findById(orderId)
                .orElseThrow(() -> new SupplyOrderNotFoundException(orderId));
        return supplyOrderMapper.toDTO(order);
    }

    @Override
    public SupplyOrderDTO createSupplyOrder(SupplyOrderDTO supplyOrderDTO) {
        SupplyOrder supplyOrder = supplyOrderMapper.toEntity(supplyOrderDTO);
        SupplyOrder savedOrder = supplyOrderRepository.save(supplyOrder);
        return supplyOrderMapper.toDTO(savedOrder);
    }

    @Override
    public SupplyOrderDTO updateSupplyOrder(UUID orderId, SupplyOrderDTO supplyOrderDTO) {
        SupplyOrder existingOrder = supplyOrderRepository.findById(orderId)
                .orElseThrow(() -> new SupplyOrderNotFoundException(orderId));

        if (supplyOrderDTO.getOrderDate() != null) {
            existingOrder.setOrderDate(supplyOrderDTO.getOrderDate());
        }
        if (supplyOrderDTO.getItems() != null) {
            existingOrder.setItems(supplyOrderDTO.getItems());
        }
        if (supplyOrderDTO.getUserId() != null) {
            User user = userRepository.findById(supplyOrderDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(supplyOrderDTO.getUserId()));
            existingOrder.setUser(user);
        }
        if (supplyOrderDTO.getStatus() != null) {
            existingOrder.setStatus(supplyOrderDTO.getStatus());
        }

        SupplyOrder updatedOrder = supplyOrderRepository.save(existingOrder);

        return supplyOrderMapper.toDTO(updatedOrder);
    }

    @Override
    public void deleteSupplyOrder(UUID orderId) {
        if (!supplyOrderRepository.existsById(orderId)) {
            throw new SupplyOrderNotFoundException(orderId);
        }
        supplyOrderRepository.deleteById(orderId);
    }
} 