package com.coworking.controller;

import com.coworking.model.Workspace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController {

    @GetMapping
    public ResponseEntity<List<Workspace>> getAllWorkspaces() {
        // TODO: Implement
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/{workspaceId}")
    public ResponseEntity<Workspace> getWorkspaceById(@PathVariable UUID workspaceId) {
        // TODO: Implement
        return ResponseEntity.ok(new Workspace());
    }

    @PostMapping
    public ResponseEntity<Workspace> createWorkspace(@RequestBody Workspace workspace) {
        // TODO: Implement
        return ResponseEntity.ok(workspace);
    }

    @PutMapping("/{workspaceId}")
    public ResponseEntity<Workspace> updateWorkspace(@PathVariable UUID workspaceId, @RequestBody Workspace workspace) {
        // TODO: Implement
        return ResponseEntity.ok(workspace);
    }

    @DeleteMapping("/{workspaceId}")
    public ResponseEntity<Void> deleteWorkspace(@PathVariable UUID workspaceId) {
        // TODO: Implement
        return ResponseEntity.noContent().build();
    }
} 