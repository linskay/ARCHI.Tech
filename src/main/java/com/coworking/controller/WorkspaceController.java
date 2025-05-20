package com.coworking.controller;

import com.coworking.model.Workspace;
import com.coworking.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        return ResponseEntity.ok(workspaceService.getAllWorkspaces());
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable UUID workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaceById(workspaceId));
    }

    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        return ResponseEntity.status(201).body(workspaceService.createWorkspace(workspace));
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable UUID workspaceId, @RequestBody Workspace workspace) {
        return ResponseEntity.ok(workspaceService.updateWorkspace(workspaceId, workspace));
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable UUID workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
        return ResponseEntity.noContent().build();
    }
} 