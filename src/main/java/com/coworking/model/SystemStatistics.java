package com.coworking.model;

import lombok.Data;

@Data
public class SystemStatistics {
    private long totalUsers;
    private long activeUsers;
    private long totalWorkspaces;
    private long availableWorkspaces;
    private long totalBookings;
    private long activeBookings;
    private long totalGuestAccess;
    private long activeGuestAccess;
    private long totalParkingSpaces;
    private long availableParkingSpaces;
    private long totalSupplyOrders;
    private long pendingSupplyOrders;
    private long totalSupportTickets;
    private long openSupportTickets;
} 