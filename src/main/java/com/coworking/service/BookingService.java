package com.coworking.service;

import com.coworking.model.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {
    Booking createBooking(Booking booking);

    Booking getBookingById(UUID bookingId);

    List<Booking> getAllBookings();

    Booking updateBooking(UUID bookingId, Booking booking);

    void deleteBooking(UUID bookingId);
}
