package com.coworking.service;

import com.coworking.dto.GuestAccessDTO;
import java.util.List;
import java.util.UUID;

public interface GuestAccessService {
    List<GuestAccessDTO> getAllGuestAccess();
    GuestAccessDTO getGuestAccessById(UUID guestAccessId);
    GuestAccessDTO createGuestAccess(GuestAccessDTO guestAccessDTO);
    GuestAccessDTO updateGuestAccess(UUID guestAccessId, GuestAccessDTO guestAccessDTO);
    void deleteGuestAccess(UUID guestAccessId);
} 