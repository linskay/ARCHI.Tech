package com.coworking.service.impl;

import com.coworking.dto.GuestAccessDTO;
import com.coworking.exception.GuestAccessNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.mapper.GuestAccessMapper;
import com.coworking.model.GuestAccess;
import com.coworking.model.User;
import com.coworking.repository.GuestAccessRepository;
import com.coworking.repository.UserRepository;
import com.coworking.service.GuestAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestAccessServiceImpl implements GuestAccessService {

    private final GuestAccessRepository guestAccessRepository;
    private final UserRepository userRepository;
    private final GuestAccessMapper guestAccessMapper;

    @Override
    public List<GuestAccessDTO> getAllGuestAccess() {
        List<GuestAccess> accesses = guestAccessRepository.findAll();
        return accesses.stream()
                .map(guestAccessMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public GuestAccessDTO getGuestAccessById(UUID guestAccessId) {
        GuestAccess guestAccess = guestAccessRepository.findById(guestAccessId)
                .orElseThrow(() -> new GuestAccessNotFoundException(guestAccessId));
        return guestAccessMapper.toDTO(guestAccess);
    }

    @Override
    public GuestAccessDTO createGuestAccess(GuestAccessDTO guestAccessDTO) {
        GuestAccess guestAccess = guestAccessMapper.toEntity(guestAccessDTO);
        GuestAccess savedGuestAccess = guestAccessRepository.save(guestAccess);
        return guestAccessMapper.toDTO(savedGuestAccess);
    }

    @Override
    public GuestAccessDTO updateGuestAccess(UUID guestAccessId, GuestAccessDTO guestAccessDTO) {
        GuestAccess existingAccess = guestAccessRepository.findById(guestAccessId)
                .orElseThrow(() -> new GuestAccessNotFoundException(guestAccessId));

        if (guestAccessDTO.getAccessStartTime() != null) {
            existingAccess.setAccessStartTime(guestAccessDTO.getAccessStartTime());
        }
        if (guestAccessDTO.getAccessEndTime() != null) {
            existingAccess.setAccessEndTime(guestAccessDTO.getAccessEndTime());
        }
        if (guestAccessDTO.getGuestName() != null) {
            existingAccess.setGuestName(guestAccessDTO.getGuestName());
        }
        if (guestAccessDTO.getStatus() != null) {
            existingAccess.setStatus(guestAccessDTO.getStatus());
        }
        if (guestAccessDTO.getGuestEmail() != null) {
            existingAccess.setGuestEmail(guestAccessDTO.getGuestEmail());
        }
        if (guestAccessDTO.getUserId() != null) {
            User user = userRepository.findById(guestAccessDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(guestAccessDTO.getUserId()));
            existingAccess.setUser(user);
        }
        GuestAccess updatedGuestAccess = guestAccessRepository.save(existingAccess);

        return guestAccessMapper.toDTO(updatedGuestAccess);
    }

    @Override
    public void deleteGuestAccess(UUID guestAccessId) {
        if (!guestAccessRepository.existsById(guestAccessId)) {
            throw new GuestAccessNotFoundException(guestAccessId);
        }
        guestAccessRepository.deleteById(guestAccessId);
    }
} 