package com.coworking.service.impl;

import com.coworking.model.Booking;
import com.coworking.repository.BookingRepository;
import com.coworking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(UUID bookingId, Booking booking) {
        if (bookingRepository.existsById(bookingId)) {
            booking.setId(bookingId);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
