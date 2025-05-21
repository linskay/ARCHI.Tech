package com.coworking.mapper;

import com.coworking.dto.GuestAccessDTO;
import com.coworking.exception.UserNotFoundException;
import com.coworking.model.GuestAccess;
import com.coworking.model.User;
import com.coworking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GuestAccessMapper {
    private final UserRepository userRepository;

    public GuestAccessDTO toDTO(GuestAccess guestAccess) {
        if (guestAccess == null) {
            return null;
        }
        GuestAccessDTO guestAccessDTO = new GuestAccessDTO();

        guestAccessDTO.setGuestAccessId(guestAccess.getGuestAccessId());
        guestAccessDTO.setUserId(guestAccess.getUser().getUserId());
        guestAccessDTO.setGuestName(guestAccess.getGuestName());
        guestAccessDTO.setGuestEmail(guestAccess.getGuestEmail());
        guestAccessDTO.setAccessStartTime(guestAccess.getAccessStartTime());
        guestAccessDTO.setAccessEndTime(guestAccess.getAccessEndTime());
        guestAccessDTO.setStatus(guestAccess.getStatus());

        return guestAccessDTO;
    }

    public GuestAccess toEntity(GuestAccessDTO guestAccessDTO) {
        if (guestAccessDTO == null) {
            return null;
        }
        GuestAccess guestAccess = new GuestAccess();

        guestAccess.setGuestAccessId(guestAccess.getGuestAccessId());
        User user = userRepository.findById(guestAccessDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(guestAccessDTO.getUserId()));
        guestAccess.setUser(user);
        guestAccess.setGuestName(guestAccessDTO.getGuestName());
        guestAccess.setGuestEmail(guestAccessDTO.getGuestEmail());
        guestAccess.setAccessStartTime(guestAccessDTO.getAccessStartTime());
        guestAccess.setAccessEndTime(guestAccessDTO.getAccessEndTime());
        guestAccess.setStatus(guestAccessDTO.getStatus());

        return guestAccess;
    }
}
