package com.coworking.repository;

import com.coworking.model.GuestAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GuestAccessRepository extends JpaRepository<GuestAccess, UUID> {
}
