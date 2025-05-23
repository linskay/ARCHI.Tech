package com.coworking.controller;

import com.coworking.dto.BookingDTO;
import com.coworking.model.Booking;
import com.coworking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Validated
@Tag(name = "Бронирования", description = "API для управления бронированиями")
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Получить все бронирования", description = "Возвращает список всех бронирований")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка",
                    content = @Content(schema = @Schema(implementation = BookingDTO.class))),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @Operation(summary = "Получить бронирование по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение бронирования",
                    content = @Content(schema = @Schema(implementation = BookingDTO.class))),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(
            @Parameter(description = "ID бронирования", required = true)
            @PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @Operation(summary = "Создать новое бронирование")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Бронирование успешно создано",
                    content = @Content(schema = @Schema(implementation = BookingDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(
            @Parameter(description = "Данные бронирования", required = true)
            @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.status(201).body(bookingService.createBooking(bookingDTO));
    }

    @Operation(summary = "Обновить бронирование по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Бронирование обновлено успешно",
                    content = @Content(schema = @Schema(implementation = BookingDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные"),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> updateBooking(
            @Parameter(description = "ID бронирования", required = true)
            @PathVariable UUID bookingId,
            @Parameter(description = "Обновленные данные бронирования", required = true)
            @RequestBody BookingDTO bookingDTO) {
        return ResponseEntity.ok(bookingService.updateBooking(bookingId, bookingDTO));
    }

    @Operation(summary = "Удалить бронирование по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Бронирование удалено успешно"),
            @ApiResponse(responseCode = "404", description = "Бронирование не найдено"),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(
            @Parameter(description = "ID бронирования", required = true)
            @PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }
}