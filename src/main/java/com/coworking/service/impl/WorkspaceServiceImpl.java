package com.coworking.service.impl;

import com.coworking.model.Workspace;
import com.coworking.service.WorkspaceService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Override
    public List<Workspace> getAllWorkspaces() {
        // TODO: Реализовать получение списка всех рабочих пространств из базы данных
        return List.of();
    }

    @Override
    public Workspace getWorkspaceById(UUID workspaceId) {
        // TODO: Реализовать получение рабочего пространства по ID из базы данных
        return null;
    }

    @Override
    public Workspace createWorkspace(Workspace workspace) {
        // TODO: Реализовать создание нового рабочего пространства в базе данных
        return workspace;
    }

    @Override
    public Workspace updateWorkspace(UUID workspaceId, Workspace workspace) {
        // TODO: Реализовать обновление данных рабочего пространства в базе данных
        return workspace;
    }

    @Override
    public void deleteWorkspace(UUID workspaceId) {
        // TODO: Реализовать удаление рабочего пространства из базы данных
    }
} 