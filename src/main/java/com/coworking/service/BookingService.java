package com.coworking.service;

import com.coworking.model.Booking;

import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);

    Booking getBookingById(Long bookingId);

    List<Booking> getAllBookings();

    Booking updateBooking(Long bookingId, Booking booking);

    void deleteBooking(Long bookingId);
}
