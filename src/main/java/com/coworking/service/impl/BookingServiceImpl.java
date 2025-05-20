package com.coworking.service.impl;

import com.coworking.model.Booking;
import com.coworking.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingService bookingService;

    public BookingServiceImpl(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingService.createBooking(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking booking) {
        return bookingService.updateBooking(bookingId, booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
