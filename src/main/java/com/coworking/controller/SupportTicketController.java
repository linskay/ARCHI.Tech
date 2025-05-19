package com.coworking.controller;

import com.coworking.model.SupportTicket;
import com.coworking.service.SupportTicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/support-tickets")
@Tag(name = "Тикеты поддержки", description = "API для управления тикетами поддержки")
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    public SupportTicketController(SupportTicketService supportTicketService) {
        this.supportTicketService = supportTicketService;
    }

    @Operation(summary = "Получить все тикеты поддержки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка",
            content = @Content(schema = @Schema(implementation = SupportTicket.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<SupportTicket>> getAllSupportTickets() {
        return ResponseEntity.ok(supportTicketService.getAllSupportTickets());
    }

    @Operation(summary = "Получить тикет поддержки по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение тикета",
            content = @Content(schema = @Schema(implementation = SupportTicket.class))),
        @ApiResponse(responseCode = "404", description = "Тикет не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{ticketId}")
    public ResponseEntity<SupportTicket> getSupportTicketById(
            @Parameter(description = "ID тикета поддержки", required = true)
            @PathVariable UUID ticketId) {
        return ResponseEntity.ok(supportTicketService.getSupportTicketById(ticketId));
    }

    @Operation(summary = "Создать новый тикет поддержки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Тикет успешно создан",
            content = @Content(schema = @Schema(implementation = SupportTicket.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<SupportTicket> createSupportTicket(
            @Parameter(description = "Данные тикета поддержки", required = true)
            @RequestBody SupportTicket supportTicket) {
        return ResponseEntity.ok(supportTicketService.createSupportTicket(supportTicket));
    }

    @Operation(summary = "Обновить тикет поддержки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Тикет успешно обновлен",
            content = @Content(schema = @Schema(implementation = SupportTicket.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "404", description = "Тикет не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{ticketId}")
    public ResponseEntity<SupportTicket> updateSupportTicket(
            @Parameter(description = "ID тикета поддержки", required = true)
            @PathVariable UUID ticketId,
            @Parameter(description = "Обновленные данные тикета", required = true)
            @RequestBody SupportTicket supportTicket) {
        return ResponseEntity.ok(supportTicketService.updateSupportTicket(ticketId, supportTicket));
    }

    @Operation(summary = "Удалить тикет поддержки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Тикет успешно удален"),
        @ApiResponse(responseCode = "404", description = "Тикет не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteSupportTicket(
            @Parameter(description = "ID тикета поддержки", required = true)
            @PathVariable UUID ticketId) {
        supportTicketService.deleteSupportTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
} 