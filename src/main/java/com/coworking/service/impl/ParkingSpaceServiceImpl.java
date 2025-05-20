package com.coworking.service.impl;

import com.coworking.exception.ParkingSpaceNotFoundException;
import com.coworking.model.ParkingSpace;
import com.coworking.repository.ParkingSpaceRepository;
import com.coworking.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;

    @Override
    public List<ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll();
    }

    @Override
    public ParkingSpace getParkingSpaceById(UUID parkingSpaceId) {
        return parkingSpaceRepository.findById(parkingSpaceId)
                .orElseThrow(() -> new ParkingSpaceNotFoundException(parkingSpaceId));
    }

    @Override
    public ParkingSpace createParkingSpace(ParkingSpace parkingSpace) {
        return parkingSpaceRepository.save(parkingSpace);
    }

    @Override
    public ParkingSpace updateParkingSpace(UUID parkingSpaceId, ParkingSpace parkingSpace) {
        ParkingSpace existingParkingSpace = getParkingSpaceById(parkingSpaceId);
        existingParkingSpace.setSpaceNumber(parkingSpace.getSpaceNumber());
        existingParkingSpace.setStatus(parkingSpace.getStatus());
        existingParkingSpace.setPricePerDay(parkingSpace.getPricePerDay());
        return parkingSpaceRepository.save(existingParkingSpace);
    }

    @Override
    public void deleteParkingSpace(UUID parkingSpaceId) {
        if (!parkingSpaceRepository.existsById(parkingSpaceId)) {
            throw new ParkingSpaceNotFoundException(parkingSpaceId);
        }
        parkingSpaceRepository.deleteById(parkingSpaceId);
    }
} 