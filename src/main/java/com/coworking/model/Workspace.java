package com.coworking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Schema(description = "Модель рабочего пространства")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Идентификатор рабочего пространства", example = "550e8400-e29b-41d4-a716-446655440")
    private UUID id;

    @Schema(description = "Название рабочего пространства", example = "Мое рабочее пространство")
    private String name;

    @Schema(description = "Описание рабочего пространства", example = "Описание рабочего пространства")
    private String description;
    
    @Column(nullable = false)
    private Double pricePerHour;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WorkspaceStatus status;
    
    public enum WorkspaceStatus {
        AVAILABLE, OCCUPIED, UNDER_MAINTENANCE
    }
} 