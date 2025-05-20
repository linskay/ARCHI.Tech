package com.coworking.service.impl;

import com.coworking.exception.GuestAccessNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.model.GuestAccess;
import com.coworking.model.User;
import com.coworking.repository.GuestAccessRepository;
import com.coworking.repository.UserRepository;
import com.coworking.service.GuestAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuestAccessServiceImpl implements GuestAccessService {

    private final GuestAccessRepository guestAccessRepository;
    private final UserRepository userRepository;

    @Override
    public List<GuestAccess> getAllGuestAccess() {
        return guestAccessRepository.findAll();
    }

    @Override
    public GuestAccess getGuestAccessById(UUID guestAccessId) {
        return guestAccessRepository.findById(guestAccessId)
                .orElseThrow(() -> new GuestAccessNotFoundException(guestAccessId));
    }

    @Override
    public GuestAccess createGuestAccess(GuestAccess guestAccess) {
        UUID userId = guestAccess.getUser().getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        guestAccess.setUser(user);
        guestAccess.setGuestName(guestAccess.getGuestName());
        guestAccess.setGuestEmail(guestAccess.getGuestEmail());
        guestAccess.setAccessStartTime(guestAccess.getAccessStartTime());
        guestAccess.setAccessEndTime(guestAccess.getAccessEndTime());
        guestAccess.setStatus(GuestAccess.GuestAccessStatus.PENDING);
        return guestAccessRepository.save(guestAccess);
    }

    @Override
    public GuestAccess updateGuestAccess(UUID guestAccessId, GuestAccess guestAccess) {
        GuestAccess existingAccess = guestAccessRepository.findById(guestAccessId)
                .orElseThrow(() -> new GuestAccessNotFoundException(guestAccessId));

        if (guestAccess.getUser() != null) {
            UUID userId = guestAccess.getUser().getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            existingAccess.setUser(user);
        }
        existingAccess.setGuestName(guestAccess.getGuestName());
        existingAccess.setGuestEmail(guestAccess.getGuestEmail());
        existingAccess.setAccessStartTime(guestAccess.getAccessStartTime());
        existingAccess.setAccessEndTime(guestAccess.getAccessEndTime());
        existingAccess.setStatus(guestAccess.getStatus());

        return guestAccessRepository.save(existingAccess);
    }

    @Override
    public void deleteGuestAccess(UUID guestAccessId) {
        if (!guestAccessRepository.existsById(guestAccessId)) {
            throw new GuestAccessNotFoundException(guestAccessId);
        }
        guestAccessRepository.deleteById(guestAccessId);
    }
} 