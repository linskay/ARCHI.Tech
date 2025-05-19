package com.coworking.service;

import com.coworking.model.Workspace;
import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    List<Workspace> getAllWorkspaces();
    Workspace getWorkspaceById(UUID workspaceId);
    Workspace createWorkspace(Workspace workspace);
    Workspace updateWorkspace(UUID workspaceId, Workspace workspace);
    void deleteWorkspace(UUID workspaceId);
} 