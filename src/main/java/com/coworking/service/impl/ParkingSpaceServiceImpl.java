package com.coworking.service.impl;

import com.coworking.model.ParkingSpace;
import com.coworking.service.ParkingSpaceService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService {

    @Override
    public List<ParkingSpace> getAllParkingSpaces() {
        // TODO: Реализовать получение всех парковочных мест
        return List.of();
    }

    @Override
    public ParkingSpace getParkingSpaceById(UUID parkingSpaceId) {
        // TODO: Реализовать получение парковочного места по ID
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingSpaceId(parkingSpaceId);
        return parkingSpace;
    }

    @Override
    public ParkingSpace createParkingSpace(ParkingSpace parkingSpace) {
        // TODO: Реализовать создание парковочного места
        parkingSpace.setParkingSpaceId(UUID.randomUUID());
        return parkingSpace;
    }

    @Override
    public ParkingSpace updateParkingSpace(UUID parkingSpaceId, ParkingSpace parkingSpace) {
        // TODO: Реализовать обновление парковочного места
        parkingSpace.setParkingSpaceId(parkingSpaceId);
        return parkingSpace;
    }

    @Override
    public void deleteParkingSpace(UUID parkingSpaceId) {
        // TODO: Реализовать удаление парковочного места
    }
} 