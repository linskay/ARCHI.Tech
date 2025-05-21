package com.coworking.dto;

import com.coworking.model.Booking;
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
public class BookingDTO {
    private UUID bookingId;


    private UUID workspaceId;


    private UUID userId;


    private LocalDateTime startTime;


    private LocalDateTime endTime;


    private Double totalPrice;


    private Booking.BookingStatus status;

}
