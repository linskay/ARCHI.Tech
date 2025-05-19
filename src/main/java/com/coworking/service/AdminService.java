package com.coworking.service;

import com.coworking.model.SystemStatistics;
import com.coworking.model.User;
import com.coworking.model.Workspace;
import java.util.List;
import java.util.UUID;

public interface AdminService {
    SystemStatistics getSystemStatistics();
    
    List<User> getAllUsers();
    User getUserById(UUID userId);
    User createUser(User user);
    User updateUser(UUID userId, User user);
    void deleteUser(UUID userId);
    
    List<Workspace> getAllWorkspaces();
    Workspace getWorkspaceById(UUID workspaceId);
    Workspace createWorkspace(Workspace workspace);
    Workspace updateWorkspace(UUID workspaceId, Workspace workspace);
    void deleteWorkspace(UUID workspaceId);
} 