package com.coworking.service.impl;

import com.coworking.exception.BookingNotFoundException;
import com.coworking.exception.SupplyOrderNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.model.Booking;
import com.coworking.model.User;
import com.coworking.repository.BookingRepository;
import com.coworking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(UUID bookingId, Booking booking) {
        Booking existingBooking = getBookingById(bookingId);

        existingBooking.setWorkspace(booking.getWorkspace());
        existingBooking.setUser(booking.getUser());
        existingBooking.setStartTime(booking.getStartTime());
        existingBooking.setEndTime(booking.getEndTime());
        existingBooking.setTotalPrice(booking.getTotalPrice());
        existingBooking.setStatus(booking.getStatus());

        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new BookingNotFoundException(bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}
