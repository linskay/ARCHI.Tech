package com.coworking.service;

import com.coworking.dto.WorkspaceDTO;
import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    List<WorkspaceDTO> getAllWorkspaces();
    WorkspaceDTO getWorkspaceById(UUID workspaceId);
    WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO);
    WorkspaceDTO updateWorkspace(UUID workspaceId, WorkspaceDTO workspaceDTO);
    void deleteWorkspace(UUID workspaceId);
} 