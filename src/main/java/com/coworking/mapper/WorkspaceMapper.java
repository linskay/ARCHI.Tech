package com.coworking.mapper;

import com.coworking.dto.WorkspaceDTO;
import com.coworking.model.Workspace;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceMapper {

    public WorkspaceDTO toDTO(Workspace workspace) {
        if (workspace == null) {
            return null;
        }
        WorkspaceDTO workspaceDTO = new WorkspaceDTO();

        workspaceDTO.setWorkspaceId(workspace.getWorkspaceId());
        workspaceDTO.setName(workspace.getName());
        workspaceDTO.setDescription(workspace.getDescription());
        workspaceDTO.setPricePerHour(workspace.getPricePerHour());
        workspaceDTO.setStatus(workspace.getStatus());

        return workspaceDTO;
    }

    public Workspace toEntity(WorkspaceDTO workspaceDTO) {
        if (workspaceDTO == null) {
            return null;
        }
        Workspace workspace = new Workspace();
        workspace.setWorkspaceId(workspaceDTO.getWorkspaceId());
        workspace.setName(workspaceDTO.getName());
        workspace.setDescription(workspaceDTO.getDescription());
        workspace.setPricePerHour(workspaceDTO.getPricePerHour());
        workspace.setStatus(workspaceDTO.getStatus());
        return workspace;
    }
}
