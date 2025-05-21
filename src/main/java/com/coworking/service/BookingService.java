package com.coworking.service;

import com.coworking.dto.BookingDTO;
import com.coworking.model.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);

    BookingDTO getBookingById(UUID bookingId);

    List<BookingDTO> getAllBookings();

    BookingDTO updateBooking(UUID bookingId, BookingDTO bookingDTO);

    void deleteBooking(UUID bookingId);
}
