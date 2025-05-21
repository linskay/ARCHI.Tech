package com.coworking.service.impl;

import com.coworking.dto.ParkingSpaceDTO;
import com.coworking.exception.ParkingSpaceNotFoundException;
import com.coworking.mapper.ParkingSpaceMapper;
import com.coworking.model.ParkingSpace;
import com.coworking.repository.ParkingSpaceRepository;
import com.coworking.service.ParkingSpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingSpaceServiceImpl implements ParkingSpaceService {
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingSpaceMapper parkingSpaceMapper;

    @Override
    public List<ParkingSpaceDTO> getAllParkingSpaces() {
        List<ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();
        return parkingSpaces.stream()
                .map(parkingSpaceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpaceDTO getParkingSpaceById(UUID parkingSpaceId) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findById(parkingSpaceId)
                .orElseThrow(() -> new ParkingSpaceNotFoundException(parkingSpaceId));
        return parkingSpaceMapper.toDTO(parkingSpace);
    }

    @Override
    public ParkingSpaceDTO createParkingSpace(ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace parkingSpace = parkingSpaceMapper.toEntity(parkingSpaceDTO);
        ParkingSpace savedParkingSpace = parkingSpaceRepository.save(parkingSpace);
        return parkingSpaceMapper.toDTO(savedParkingSpace);
    }

    @Override
    public ParkingSpaceDTO updateParkingSpace(UUID parkingSpaceId, ParkingSpaceDTO parkingSpaceDTO) {
        ParkingSpace existingSpace = parkingSpaceRepository.findById(parkingSpaceId)
                .orElseThrow(() -> new ParkingSpaceNotFoundException(parkingSpaceId));

        if (parkingSpaceDTO.getSpaceNumber() != null) {
            existingSpace.setSpaceNumber(parkingSpaceDTO.getSpaceNumber());
        }
        if (parkingSpaceDTO.getPricePerDay() != null) {
            existingSpace.setPricePerDay(parkingSpaceDTO.getPricePerDay());
        }
        if (parkingSpaceDTO.getStatus() != null) {
            existingSpace.setStatus(parkingSpaceDTO.getStatus());
        }
        ParkingSpace updatedParkingSpace = parkingSpaceRepository.save(existingSpace);

        return parkingSpaceMapper.toDTO(updatedParkingSpace);
    }

    @Override
    public void deleteParkingSpace(UUID parkingSpaceId) {
        if (!parkingSpaceRepository.existsById(parkingSpaceId)) {
            throw new ParkingSpaceNotFoundException(parkingSpaceId);
        }
        parkingSpaceRepository.deleteById(parkingSpaceId);
    }
} 