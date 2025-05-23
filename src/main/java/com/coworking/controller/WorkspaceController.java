package com.coworking.controller;

import com.coworking.dto.WorkspaceDTO;
import com.coworking.service.WorkspaceService;
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
@RequestMapping("/workspaces")
@RequiredArgsConstructor
@Validated
@Tag(name = "Рабочие пространства", description = "API для управления рабочими пространствами")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Operation(summary = "Получить все рабочие пространства", description = "Возвращает список всех рабочих пространств")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Список рабочих пространств успешно получен",
            content = @Content(schema = @Schema(implementation = WorkspaceDTO.class))),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping
    public ResponseEntity<List<WorkspaceDTO>> getAllWorkspaces() {
        return ResponseEntity.ok(workspaceService.getAllWorkspaces());
    }

    @Operation(summary = "Получить рабочее пространство по ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Рабочее пространство успешно найдено",
            content = @Content(schema = @Schema(implementation = WorkspaceDTO.class))),
        @ApiResponse(responseCode = "404", description = "Рабочее пространство не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> getWorkspaceById(
            @Parameter(description = "ID рабочего пространства", required = true)
            @PathVariable UUID workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaceById(workspaceId));
    }

    @Operation(summary = "Создать новое рабочее пространство")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Рабочее пространство успешно создано",
            content = @Content(schema = @Schema(implementation = WorkspaceDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PostMapping
    public ResponseEntity<WorkspaceDTO> createWorkspace(
            @Parameter(description = "Данные рабочего пространства", required = true)
            @RequestBody WorkspaceDTO workspaceDTO) {
        return ResponseEntity.status(201).body(workspaceService.createWorkspace(workspaceDTO));
    }

    @Operation(summary = "Обновить рабочее пространство")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Рабочее пространство успешно обновлено",
            content = @Content(schema = @Schema(implementation = WorkspaceDTO.class))),
        @ApiResponse(responseCode = "400", description = "Некорректные данные"),
        @ApiResponse(responseCode = "404", description = "Рабочее пространство не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(
            @Parameter(description = "ID рабочего пространства", required = true)
            @PathVariable UUID workspaceId,
            @Parameter(description = "Обновленные данные рабочего пространства", required = true)
            @RequestBody WorkspaceDTO workspaceDTO) {
        return ResponseEntity.ok(workspaceService.updateWorkspace(workspaceId, workspaceDTO));
    }

    @Operation(summary = "Удалить рабочее пространство")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Рабочее пространство успешно удалено"),
        @ApiResponse(responseCode = "404", description = "Рабочее пространство не найдено"),
        @ApiResponse(responseCode = "401", description = "Неавторизованный доступ"),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(
            @Parameter(description = "ID рабочего пространства", required = true)
            @PathVariable UUID workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }
}