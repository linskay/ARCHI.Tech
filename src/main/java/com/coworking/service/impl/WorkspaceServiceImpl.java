package com.coworking.service.impl;

import com.coworking.exception.WorkspaceNotFoundException;
import com.coworking.model.Workspace;
import com.coworking.repository.WorkspaceRepository;
import com.coworking.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;

    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.findAll();
    }

    @Override
    public Workspace getWorkspaceById(UUID workspaceId) {
       return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException(workspaceId));
    }

    @Override
    public Workspace createWorkspace(Workspace workspace) {
        return workspaceRepository.save(workspace);
    }

    @Override
    public Workspace updateWorkspace(UUID workspaceId, Workspace workspace) {
        Workspace existingWorkspace = getWorkspaceById(workspaceId);

        existingWorkspace.setName(workspace.getName());
        existingWorkspace.setDescription(workspace.getDescription());
        existingWorkspace.setPricePerHour(workspace.getPricePerHour());
        existingWorkspace.setStatus(workspace.getStatus());

        return workspaceRepository.save(existingWorkspace);
    }

    @Override
    public void deleteWorkspace(UUID workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new WorkspaceNotFoundException(workspaceId);
        }
        workspaceRepository.deleteById(workspaceId);
    }
} 