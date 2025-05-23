package com.coworking.controller;

import com.coworking.dto.UserDTO;
import com.coworking.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Пользователи", description = "API для управления пользователями")
public class UserController {
    private final UserService userService;

    @Operation(summary = "Получить всех пользователей", description = "Возвращает список всех пользователей")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Получить пользователя по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно найден",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @Operation(summary = "Создать нового пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Пользователь успешно создан",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @Parameter(description = "Данные пользователя", required = true)
            @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.createUser(userDTO));
    }

    @Operation(summary = "Обновить пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable UUID userId,
            @Parameter(description = "Обновленные данные пользователя", required = true)
            @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }

    @Operation(summary = "Удалить пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
        @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID пользователя", required = true)
            @PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}