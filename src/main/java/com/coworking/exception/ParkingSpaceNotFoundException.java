package com.coworking.exception;

import java.util.UUID;

public class ParkingSpaceNotFoundException extends RuntimeException {
    public ParkingSpaceNotFoundException(UUID parkingSpaceId) {
        super("ParkingSpace not found with ID: " + parkingSpaceId);
    }
}
