package com.coworking.repository;

import com.coworking.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpaceRepository extends JpaRepository <ParkingSpace, UUID> {
}
