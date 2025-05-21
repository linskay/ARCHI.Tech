package com.coworking.dto;

import com.coworking.model.GuestAccess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestAccessDTO {
    private UUID guestAccessId;

    private UUID userId;

    private String guestName;

    private String guestEmail;

    private LocalDateTime accessStartTime;

    private LocalDateTime accessEndTime;

    private GuestAccess.GuestAccessStatus status;
}
