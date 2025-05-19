package com.coworking.service;

import com.coworking.model.ParkingSpace;
import java.util.List;
import java.util.UUID;

public interface ParkingSpaceService {
    List<ParkingSpace> getAllParkingSpaces();
    ParkingSpace getParkingSpaceById(UUID parkingSpaceId);
    ParkingSpace createParkingSpace(ParkingSpace parkingSpace);
    ParkingSpace updateParkingSpace(UUID parkingSpaceId, ParkingSpace parkingSpace);
    void deleteParkingSpace(UUID parkingSpaceId);
} 