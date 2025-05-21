package com.coworking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID parkingSpaceId;
    
    @Column(nullable = false, length = 10)
    private String spaceNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ParkingSpaceStatus status;
    
    @Column(nullable = false)
    private Double pricePerDay;
    
    public enum ParkingSpaceStatus {
        AVAILABLE, OCCUPIED, RESERVED
    }
} 