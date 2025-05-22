package com.coworking.controller;

import com.coworking.dto.SupplyOrderDTO;
import com.coworking.model.SupplyOrder;
import com.coworking.service.SupplyOrderService;
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
@RequiredArgsConstructor
@Validated
@RequestMapping("/supply-orders")
@Tag(name = "Заказы поставок", description = "API для управления заказами поставок")
public class SupplyOrderController {

    private final SupplyOrderService supplyOrderService;

    @Operation(summary = "Получить все заказы поставок", description = "Возвращает список всех заказов поставок")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное получение списка",
                    content = @Content(schema = @Schema(implementation = SupplyOrder.class))),
            @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<SupplyOrderDTO>> getAllSupplyOrders() {
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
    public ResponseEntity<SupplyOrderDTO> getSupplyOrderById(
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
    public ResponseEntity<SupplyOrderDTO> createSupplyOrder(
            @Parameter(description = "Данные заказа поставки", required = true)
            @RequestBody SupplyOrderDTO supplyOrderDTO) {
        return ResponseEntity.status(201).body(supplyOrderService.createSupplyOrder(supplyOrderDTO));
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
    public ResponseEntity<SupplyOrderDTO> updateSupplyOrder(
            @Parameter(description = "ID заказа поставки", required = true)
            @PathVariable UUID orderId,
            @Parameter(description = "Обновленные данные заказа", required = true)
            @RequestBody SupplyOrderDTO supplyOrderDTO) {
        return ResponseEntity.ok(supplyOrderService.updateSupplyOrder(orderId, supplyOrderDTO));
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