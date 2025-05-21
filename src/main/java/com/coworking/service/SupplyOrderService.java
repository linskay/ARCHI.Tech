package com.coworking.service;

import com.coworking.dto.SupplyOrderDTO;
import com.coworking.model.SupplyOrder;
import java.util.List;
import java.util.UUID;

public interface SupplyOrderService {
    List<SupplyOrderDTO> getAllSupplyOrders();
    SupplyOrderDTO getSupplyOrderById(UUID orderId);
    SupplyOrderDTO createSupplyOrder(SupplyOrderDTO supplyOrderDTO);
    SupplyOrderDTO updateSupplyOrder(UUID orderId, SupplyOrderDTO supplyOrderDTO);
    void deleteSupplyOrder(UUID orderId);
} 