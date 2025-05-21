package com.coworking.controller;

import com.coworking.dto.WorkspaceDTO;
import com.coworking.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
@Validated
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<WorkspaceDTO>> getAllWorkspaces() {
        return ResponseEntity.ok(workspaceService.getAllWorkspaces());
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> getWorkspaceById(@PathVariable UUID workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaceById(workspaceId));
    }

    @PostMapping
    public ResponseEntity<WorkspaceDTO> createWorkspace(@RequestBody WorkspaceDTO workspaceDTO) {
        return ResponseEntity.status(201).body(workspaceService.createWorkspace(workspaceDTO));
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> updateWorkspace(@PathVariable UUID workspaceId, @RequestBody WorkspaceDTO workspaceDTO) {
        return ResponseEntity.ok(workspaceService.updateWorkspace(workspaceId, workspaceDTO));
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable UUID workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }
} 