package com.coworking.service;

import com.coworking.model.SupplyOrder;
import java.util.List;
import java.util.UUID;

public interface SupplyOrderService {
    List<SupplyOrder> getAllSupplyOrders();
    SupplyOrder getSupplyOrderById(UUID orderId);
    SupplyOrder createSupplyOrder(SupplyOrder supplyOrder);
    SupplyOrder updateSupplyOrder(UUID orderId, SupplyOrder supplyOrder);
    void deleteSupplyOrder(UUID orderId);
} 