package com.coworking.exception;

import java.util.UUID;

public class WorkspaceNotFoundException extends RuntimeException {
    public WorkspaceNotFoundException(UUID workspaceId) {
        super("Workspace not found with ID: " + workspaceId);
    }
}
