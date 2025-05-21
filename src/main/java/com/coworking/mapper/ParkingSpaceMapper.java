package com.coworking.mapper;

import com.coworking.dto.ParkingSpaceDTO;
import com.coworking.model.ParkingSpace;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpaceMapper {
    public ParkingSpaceDTO toDTO(ParkingSpace parkingSpace) {
        if (parkingSpace == null) {
            return null;
        }
        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();

        parkingSpaceDTO.setParkingSpaceId(parkingSpace.getParkingSpaceId());
        parkingSpaceDTO.setSpaceNumber(parkingSpace.getSpaceNumber());
        parkingSpaceDTO.setPricePerDay(parkingSpace.getPricePerDay());
        parkingSpaceDTO.setStatus(parkingSpace.getStatus());

        return parkingSpaceDTO;
    }

    public ParkingSpace toEntity(ParkingSpaceDTO parkingSpaceDTO) {
        if (parkingSpaceDTO == null) {
            return null;
        }
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setParkingSpaceId(parkingSpaceDTO.getParkingSpaceId());
        parkingSpace.setSpaceNumber(parkingSpaceDTO.getSpaceNumber());
        parkingSpace.setPricePerDay(parkingSpaceDTO.getPricePerDay());
        parkingSpace.setStatus(parkingSpaceDTO.getStatus());
        return parkingSpace;
    }
}
