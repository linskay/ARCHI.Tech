package com.coworking.controller;

import com.coworking.model.ParkingSpace;
import com.coworking.service.ParkingSpaceService;
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
@RequestMapping("/parking-spaces")
@Tag(name = "Парковочные места", description = "API для управления парковочными местами")
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @Operation(summary = "Получить все парковочные места")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка",
            content = @Content(schema = @Schema(implementation = ParkingSpace.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAllParkingSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAllParkingSpaces());
    }

    @Operation(summary = "Получить парковочное место по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение парковочного места",
            content = @Content(schema = @Schema(implementation = ParkingSpace.class))),
        @ApiResponse(responseCode = "404", description = "Парковочное место не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{parkingSpaceId}")
    public ResponseEntity<ParkingSpace> getParkingSpaceById(
            @Parameter(description = "ID парковочного места", required = true)
            @PathVariable UUID parkingSpaceId) {
        return ResponseEntity.ok(parkingSpaceService.getParkingSpaceById(parkingSpaceId));
    }

    @Operation(summary = "Создать новое парковочное место")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Парковочное место успешно создано",
            content = @Content(schema = @Schema(implementation = ParkingSpace.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<ParkingSpace> createParkingSpace(
            @Parameter(description = "Данные парковочного места", required = true)
            @RequestBody ParkingSpace parkingSpace) {
        return ResponseEntity.ok(parkingSpaceService.createParkingSpace(parkingSpace));
    }

    @Operation(summary = "Обновить парковочное место")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Парковочное место успешно обновлено",
            content = @Content(schema = @Schema(implementation = ParkingSpace.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "404", description = "Парковочное место не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{parkingSpaceId}")
    public ResponseEntity<ParkingSpace> updateParkingSpace(
            @Parameter(description = "ID парковочного места", required = true)
            @PathVariable UUID parkingSpaceId,
            @Parameter(description = "Обновленные данные парковочного места", required = true)
            @RequestBody ParkingSpace parkingSpace) {
        return ResponseEntity.ok(parkingSpaceService.updateParkingSpace(parkingSpaceId, parkingSpace));
    }

    @Operation(summary = "Удалить парковочное место")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Парковочное место успешно удалено"),
        @ApiResponse(responseCode = "404", description = "Парковочное место не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{parkingSpaceId}")
    public ResponseEntity<Void> deleteParkingSpace(
            @Parameter(description = "ID парковочного места", required = true)
            @PathVariable UUID parkingSpaceId) {
        parkingSpaceService.deleteParkingSpace(parkingSpaceId);
        return ResponseEntity.noContent().build();
    }
} 