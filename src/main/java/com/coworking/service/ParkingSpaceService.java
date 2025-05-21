package com.coworking.service;

import com.coworking.dto.ParkingSpaceDTO;
import java.util.List;
import java.util.UUID;

public interface ParkingSpaceService {
    List<ParkingSpaceDTO> getAllParkingSpaces();
    ParkingSpaceDTO getParkingSpaceById(UUID parkingSpaceId);
    ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO);
    ParkingSpaceDTO updateParkingSpace(UUID parkingSpaceId, ParkingSpaceDTO parkingSpaceDTO);
    void deleteParkingSpace(UUID parkingSpaceId);
} 