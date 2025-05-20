package com.coworking.controller;

import com.coworking.model.SupplyOrder;
import com.coworking.service.SupplyOrderService;
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
@RequestMapping("/supply-orders")
@Tag(name = "Заказы поставок", description = "API для управления заказами поставок")
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;

    public SupplyOrderController(SupplyOrderService supplyOrderService) {
        this.supplyOrderService = supplyOrderService;
    }

    @Operation(summary = "Получить все заказы поставок")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка",
            content = @Content(schema = @Schema(implementation = SupplyOrder.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<SupplyOrder>> getAllSupplyOrders() {
        return ResponseEntity.ok(supplyOrderService.getAllSupplyOrders());
    }

    @Operation(summary = "Получить заказ поставки по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение заказа",
            content = @Content(schema = @Schema(implementation = SupplyOrder.class))),
        @ApiResponse(responseCode = "404", description = "Заказ не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<SupplyOrder> getSupplyOrderById(
            @Parameter(description = "ID заказа поставки", required = true)
            @PathVariable UUID orderId) {
        return ResponseEntity.ok(supplyOrderService.getSupplyOrderById(orderId));
    }

    @Operation(summary = "Создать новый заказ поставки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Заказ успешно создан",
            content = @Content(schema = @Schema(implementation = SupplyOrder.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<SupplyOrder> createSupplyOrder(
            @Parameter(description = "Данные заказа поставки", required = true)
            @RequestBody SupplyOrder supplyOrder) {
        return ResponseEntity.status(201).body(supplyOrderService.createSupplyOrder(supplyOrder));
    }

    @Operation(summary = "Обновить заказ поставки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказ успешно обновлен",
            content = @Content(schema = @Schema(implementation = SupplyOrder.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "404", description = "Заказ не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{orderId}")
    public ResponseEntity<SupplyOrder> updateSupplyOrder(
            @Parameter(description = "ID заказа поставки", required = true)
            @PathVariable UUID orderId,
            @Parameter(description = "Обновленные данные заказа", required = true)
            @RequestBody SupplyOrder supplyOrder) {
        return ResponseEntity.ok(supplyOrderService.updateSupplyOrder(orderId, supplyOrder));
    }

    @Operation(summary = "Удалить заказ поставки")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Заказ успешно удален"),
        @ApiResponse(responseCode = "404", description = "Заказ не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteSupplyOrder(
            @Parameter(description = "ID заказа поставки", required = true)
            @PathVariable UUID orderId) {
        supplyOrderService.deleteSupplyOrder(orderId);
        return ResponseEntity.noContent().build();
    }
} 