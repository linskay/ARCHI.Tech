package com.coworking.service;

import com.coworking.model.GuestAccess;
import java.util.List;
import java.util.UUID;

public interface GuestAccessService {
    List<GuestAccess> getAllGuestAccess();
    GuestAccess getGuestAccessById(UUID guestAccessId);
    GuestAccess createGuestAccess(GuestAccess guestAccess);
    GuestAccess updateGuestAccess(UUID guestAccessId, GuestAccess guestAccess);
    void deleteGuestAccess(UUID guestAccessId);
} 