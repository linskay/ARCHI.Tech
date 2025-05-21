package com.coworking.service;

import com.coworking.dto.WorkspaceDTO;
import com.coworking.model.SystemStatistics;
import com.coworking.model.User;
import java.util.List;
import java.util.UUID;

public interface AdminService {
    SystemStatistics getSystemStatistics();
    
    List<User> getAllUsers();
    User getUserById(UUID userId);
    User createUser(User user);
    User updateUser(UUID userId, User user);
    void deleteUser(UUID userId);
    
    List<WorkspaceDTO> getAllWorkspaces();
    WorkspaceDTO getWorkspaceById(UUID workspaceId);
    WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO);
    WorkspaceDTO updateWorkspace(UUID workspaceId, WorkspaceDTO workspaceDTO);
    void deleteWorkspace(UUID workspaceId);
} 