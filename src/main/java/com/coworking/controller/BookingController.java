package com.coworking.controller;

import com.coworking.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        // TODO: Implement
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID bookingId) {
        // TODO: Implement
        return ResponseEntity.ok(new Booking());
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        // TODO: Implement
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable UUID bookingId, @RequestBody Booking booking) {
        // TODO: Implement
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        // TODO: Implement
        return ResponseEntity.noContent().build();
    }
} 