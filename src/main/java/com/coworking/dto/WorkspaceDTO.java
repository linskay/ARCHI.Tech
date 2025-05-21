package com.coworking.dto;

import com.coworking.model.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceDTO {
    private UUID workspaceId;

    private String name;

    private String description;

    private Double pricePerHour;

    private Workspace.WorkspaceStatus status;
}
