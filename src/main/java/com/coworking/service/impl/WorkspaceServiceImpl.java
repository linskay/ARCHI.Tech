package com.coworking.service.impl;

import com.coworking.dto.WorkspaceDTO;
import com.coworking.exception.WorkspaceNotFoundException;
import com.coworking.mapper.WorkspaceMapper;
import com.coworking.model.Workspace;
import com.coworking.repository.WorkspaceRepository;
import com.coworking.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public List<WorkspaceDTO> getAllWorkspaces() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return workspaces.stream()
                .map(workspaceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public WorkspaceDTO getWorkspaceById(UUID workspaceId) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException(workspaceId));
        return workspaceMapper.toDTO(workspace);
    }

    @Override
    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO) {
        Workspace workspace = workspaceMapper.toEntity(workspaceDTO);
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        return workspaceMapper.toDTO(savedWorkspace);
    }

    @Override
    public WorkspaceDTO updateWorkspace(UUID workspaceId, WorkspaceDTO workspaceDTO) {
        Workspace existingWorkspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException(workspaceId));

        if (workspaceDTO.getName() != null) {
            existingWorkspace.setName(workspaceDTO.getName());
        }
        if (workspaceDTO.getDescription() != null) {
            existingWorkspace.setDescription(workspaceDTO.getDescription());
        }
        if (workspaceDTO.getPricePerHour() != null) {
            existingWorkspace.setPricePerHour(workspaceDTO.getPricePerHour());
        }
        if (workspaceDTO.getStatus() != null) {
            existingWorkspace.setStatus(workspaceDTO.getStatus());
        }
        Workspace updatedWorkspace = workspaceRepository.save(existingWorkspace);

        return workspaceMapper.toDTO(updatedWorkspace);
    }

    @Override
    public void deleteWorkspace(UUID workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new WorkspaceNotFoundException(workspaceId);
        }
        workspaceRepository.deleteById(workspaceId);
    }
} 