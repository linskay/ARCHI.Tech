package com.coworking.service;

import com.coworking.dto.UserDTO;
import com.coworking.dto.WorkspaceDTO;
import com.coworking.model.SystemStatistics;
import java.util.List;
import java.util.UUID;

public interface AdminService {
    SystemStatistics getSystemStatistics();
    
    List<UserDTO> getAllUsers();
    UserDTO getUserById(UUID userId);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(UUID userId, UserDTO userDTO);
    void deleteUser(UUID userId);
    
    List<WorkspaceDTO> getAllWorkspaces();
    WorkspaceDTO getWorkspaceById(UUID workspaceId);
    WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO);
    WorkspaceDTO updateWorkspace(UUID workspaceId, WorkspaceDTO workspaceDTO);
    void deleteWorkspace(UUID workspaceId);
} 