package com.coworking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "parking_spaces")
public class ParkingSpace {
    @Id
    @Column(name = "parking_space_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID parkingSpaceId;
    
    @Column(name = "space_number", nullable = false, length = 10, unique = true)
    private String spaceNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private ParkingSpaceStatus status;
    
    @Column(name = "price_per_day", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerDay;
    
    public enum ParkingSpaceStatus {
        AVAILABLE, OCCUPIED, RESERVED
    }
} 