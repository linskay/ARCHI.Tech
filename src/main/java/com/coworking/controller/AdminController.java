package com.coworking.controller;

import com.coworking.model.*;
import com.coworking.service.AdminService;
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
@RequestMapping("/admin")
@Tag(name = "Админ-панель", description = "API для административного управления системой")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @Operation(summary = "Получить статистику системы")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Статистика успешно получена"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/statistics")
    public ResponseEntity<SystemStatistics> getSystemStatistics() {
        return ResponseEntity.ok(adminService.getSystemStatistics());
    }

    @Operation(summary = "Получить список всех пользователей")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно найден"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "ID пользователя") @PathVariable UUID userId) {
        return ResponseEntity.ok(adminService.getUserById(userId));
    }

    @Operation(summary = "Создать нового пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно создан"),
        @ApiResponse(responseCode = "400", description = "Неверные данные пользователя"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping("/users")
    public ResponseEntity<User> createUser(
            @Parameter(description = "Данные пользователя") @RequestBody User user) {
        return ResponseEntity.ok(adminService.createUser(user));
    }

    @Operation(summary = "Обновить данные пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
        @ApiResponse(responseCode = "400", description = "Неверные данные пользователя"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "ID пользователя") @PathVariable UUID userId,
            @Parameter(description = "Новые данные пользователя") @RequestBody User user) {
        return ResponseEntity.ok(adminService.updateUser(userId, user));
    }

    @Operation(summary = "Удалить пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID пользователя") @PathVariable UUID userId) {
        adminService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить список всех рабочих пространств")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список рабочих пространств успешно получен"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/workspaces")
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        return ResponseEntity.ok(adminService.getAllWorkspaces());
    }

    @Operation(summary = "Получить рабочее пространство по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Рабочее пространство успешно найдено"),
        @ApiResponse(responseCode = "404", description = "Рабочее пространство не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/workspaces/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspaceById(
            @Parameter(description = "ID рабочего пространства") @PathVariable UUID workspaceId) {
        return ResponseEntity.ok(adminService.getWorkspaceById(workspaceId));
    }

    @Operation(summary = "Создать новое рабочее пространство")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Рабочее пространство успешно создано"),
        @ApiResponse(responseCode = "400", description = "Неверные данные рабочего пространства"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping("/workspaces")
    public ResponseEntity<Workspace> createWorkspace(
            @Parameter(description = "Данные рабочего пространства") @RequestBody Workspace workspace) {
        return ResponseEntity.ok(adminService.createWorkspace(workspace));
    }

    @Operation(summary = "Обновить данные рабочего пространства")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Рабочее пространство успешно обновлено"),
        @ApiResponse(responseCode = "400", description = "Неверные данные рабочего пространства"),
        @ApiResponse(responseCode = "404", description = "Рабочее пространство не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/workspaces/{workspaceId}")
    public ResponseEntity<Workspace> updateWorkspace(
            @Parameter(description = "ID рабочего пространства") @PathVariable UUID workspaceId,
            @Parameter(description = "Новые данные рабочего пространства") @RequestBody Workspace workspace) {
        return ResponseEntity.ok(adminService.updateWorkspace(workspaceId, workspace));
    }

    @Operation(summary = "Удалить рабочее пространство")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Рабочее пространство успешно удалено"),
        @ApiResponse(responseCode = "404", description = "Рабочее пространство не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "403", description = "Доступ запрещен"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/workspaces/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(
            @Parameter(description = "ID рабочего пространства") @PathVariable UUID workspaceId) {
        adminService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }
} 