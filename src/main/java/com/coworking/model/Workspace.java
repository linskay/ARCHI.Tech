package com.coworking.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "workspaces")
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID workspaceId;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column
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