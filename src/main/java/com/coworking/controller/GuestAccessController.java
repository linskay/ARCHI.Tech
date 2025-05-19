package com.coworking.controller;

import com.coworking.model.GuestAccess;
import com.coworking.service.GuestAccessService;
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
@RequestMapping("/guest-access")
@Tag(name = "Гостевой доступ", description = "API для управления гостевым доступом")
public class GuestAccessController {

    private final GuestAccessService guestAccessService;

    public GuestAccessController(GuestAccessService guestAccessService) {
        this.guestAccessService = guestAccessService;
    }

    @Operation(summary = "Получить все запросы на гостевой доступ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение списка",
            content = @Content(schema = @Schema(implementation = GuestAccess.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<GuestAccess>> getAllGuestAccess() {
        return ResponseEntity.ok(guestAccessService.getAllGuestAccess());
    }

    @Operation(summary = "Получить запрос на гостевой доступ по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешное получение запроса",
            content = @Content(schema = @Schema(implementation = GuestAccess.class))),
        @ApiResponse(responseCode = "404", description = "Запрос не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{guestAccessId}")
    public ResponseEntity<GuestAccess> getGuestAccessById(
            @Parameter(description = "ID запроса на гостевой доступ", required = true)
            @PathVariable UUID guestAccessId) {
        return ResponseEntity.ok(guestAccessService.getGuestAccessById(guestAccessId));
    }

    @Operation(summary = "Создать новый запрос на гостевой доступ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос успешно создан",
            content = @Content(schema = @Schema(implementation = GuestAccess.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<GuestAccess> createGuestAccess(
            @Parameter(description = "Данные запроса на гостевой доступ", required = true)
            @RequestBody GuestAccess guestAccess) {
        return ResponseEntity.ok(guestAccessService.createGuestAccess(guestAccess));
    }

    @Operation(summary = "Обновить запрос на гостевой доступ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Запрос успешно обновлен",
            content = @Content(schema = @Schema(implementation = GuestAccess.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "404", description = "Запрос не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{guestAccessId}")
    public ResponseEntity<GuestAccess> updateGuestAccess(
            @Parameter(description = "ID запроса на гостевой доступ", required = true)
            @PathVariable UUID guestAccessId,
            @Parameter(description = "Обновленные данные запроса", required = true)
            @RequestBody GuestAccess guestAccess) {
        return ResponseEntity.ok(guestAccessService.updateGuestAccess(guestAccessId, guestAccess));
    }

    @Operation(summary = "Удалить запрос на гостевой доступ")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Запрос успешно удален"),
        @ApiResponse(responseCode = "404", description = "Запрос не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{guestAccessId}")
    public ResponseEntity<Void> deleteGuestAccess(
            @Parameter(description = "ID запроса на гостевой доступ", required = true)
            @PathVariable UUID guestAccessId) {
        guestAccessService.deleteGuestAccess(guestAccessId);
        return ResponseEntity.noContent().build();
    }
} 