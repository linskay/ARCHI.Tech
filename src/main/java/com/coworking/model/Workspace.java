package com.coworking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Модель рабочего пространства")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Идентификатор рабочего пространства", example = "550e8400-e29b-41d4-a716-446655440")
    @Column(name = "workspace_id")
    private UUID workspaceId;
    
    @Schema(description = "Название рабочего пространства", example = "Мое рабочее пространство")
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Schema(description = "Описание рабочего пространства", example = "Описание рабочего пространства")
    @Column (name = "description", columnDefinition = "TEXT")

    private String description;
    
    @Column(name = "price_per_hour", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerHour;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private WorkspaceStatus status;
    
    public enum WorkspaceStatus {
        AVAILABLE, OCCUPIED, UNDER_MAINTENANCE
    }
} 