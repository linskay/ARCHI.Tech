package com.coworking.service.impl;

import com.coworking.model.SupplyOrder;
import com.coworking.service.SupplyOrderService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SupplyOrderServiceImpl implements SupplyOrderService {

    @Override
    public List<SupplyOrder> getAllSupplyOrders() {
        // TODO: Реализовать получение всех заказов поставок
        return List.of();
    }

    @Override
    public SupplyOrder getSupplyOrderById(UUID orderId) {
        // TODO: Реализовать получение заказа поставки по ID
        SupplyOrder supplyOrder = new SupplyOrder();
        supplyOrder.setOrderId(orderId);
        return supplyOrder;
    }

    @Override
    public SupplyOrder createSupplyOrder(SupplyOrder supplyOrder) {
        // TODO: Реализовать создание заказа поставки
        supplyOrder.setOrderId(UUID.randomUUID());
        return supplyOrder;
    }

    @Override
    public SupplyOrder updateSupplyOrder(UUID orderId, SupplyOrder supplyOrder) {
        // TODO: Реализовать обновление заказа поставки
        supplyOrder.setOrderId(orderId);
        return supplyOrder;
    }

    @Override
    public void deleteSupplyOrder(UUID orderId) {
        // TODO: Реализовать удаление заказа поставки
    }
} 