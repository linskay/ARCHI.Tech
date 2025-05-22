package com.coworking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table (name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "workspace_id")
    private UUID workspaceId;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
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