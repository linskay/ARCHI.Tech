package com.coworking.exception;

import java.util.UUID;

public class SupplyOrderNotFoundException extends RuntimeException {
    public SupplyOrderNotFoundException(UUID orderId) {
        super("Order not found with ID: " + orderId);
    }
}
