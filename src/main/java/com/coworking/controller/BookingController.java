package com.coworking.controller;

import com.coworking.model.Booking;
import com.coworking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Получить все бронирования")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(summary = "Получить бронирование по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное бронирование",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @Operation(summary = "Создать новое бронирование")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Бронирование успешно создано",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(201).body(bookingService.createBooking(booking));
    }

    @Operation(summary = "Обновить бронирование по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирование обновлено успешно",
                    content = @Content(schema = @Schema(implementation = Booking.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable UUID bookingId, @RequestBody Booking booking) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, booking));
    }

    @Operation(summary = "Удалить бронирование по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Бронирование удалено успешно"),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
} 