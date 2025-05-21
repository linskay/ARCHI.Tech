package com.coworking.dto;

import com.coworking.model.ParkingSpace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpaceDTO {
    private UUID parkingSpaceId;

    private String spaceNumber;

    private Double pricePerDay;

    private ParkingSpace.ParkingSpaceStatus status;


}
