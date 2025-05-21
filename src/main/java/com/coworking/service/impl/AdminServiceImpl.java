package com.coworking.service.impl;

import com.coworking.dto.WorkspaceDTO;
import com.coworking.model.SystemStatistics;
import com.coworking.model.User;
import com.coworking.service.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserService userService;
    private final WorkspaceService workspaceService;
    private final BookingService bookingService;
    private final GuestAccessService guestAccessService;
    private final ParkingSpaceService parkingSpaceService;
    private final SupplyOrderService supplyOrderService;
    private final SupportTicketService supportTicketService;

    public AdminServiceImpl(
            UserService userService,
            WorkspaceService workspaceService,
            BookingService bookingService,
            GuestAccessService guestAccessService,
            ParkingSpaceService parkingSpaceService,
            SupplyOrderService supplyOrderService,
            SupportTicketService supportTicketService) {
        this.userService = userService;
        this.workspaceService = workspaceService;
        this.bookingService = bookingService;
        this.guestAccessService = guestAccessService;
        this.parkingSpaceService = parkingSpaceService;
        this.supplyOrderService = supplyOrderService;
        this.supportTicketService = supportTicketService;
    }

    @Override
    public SystemStatistics getSystemStatistics() {
        SystemStatistics statistics = new SystemStatistics();
        
        // TODO: Реализовать получение статистики из всех сервисов
        // Например:
        // statistics.setTotalUsers(userService.countAllUsers());
        // statistics.setActiveUsers(userService.countActiveUsers());
        // и т.д.
        
        return statistics;
    }

    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public User getUserById(UUID userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(UUID userId, User user) {
        return userService.updateUser(userId, user);
    }

    @Override
    public void deleteUser(UUID userId) {
        userService.deleteUser(userId);
    }

    @Override
    public List<WorkspaceDTO> getAllWorkspaces() {
        return workspaceService.getAllWorkspaces();
    }

    @Override
    public WorkspaceDTO getWorkspaceById(UUID workspaceId) {
        return workspaceService.getWorkspaceById(workspaceId);
    }

    @Override
    public WorkspaceDTO createWorkspace(WorkspaceDTO workspaceDTO) {
        return workspaceService.createWorkspace(workspaceDTO);
    }

    @Override
    public WorkspaceDTO updateWorkspace(UUID workspaceId, WorkspaceDTO workspaceDTO) {
        return workspaceService.updateWorkspace(workspaceId, workspaceDTO);
    }

    @Override
    public void deleteWorkspace(UUID workspaceId) {
        workspaceService.deleteWorkspace(workspaceId);
    }
} 