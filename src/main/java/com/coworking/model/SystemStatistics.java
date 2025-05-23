package com.coworking.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Модель системной статистики")
@Data
public class SystemStatistics {
    @Schema(description = "Общее количество пользователей", example = "100")
    private long totalUsers;

    @Schema(description = "Количество активных пользователей", example = "75")
    private long activeUsers;

    @Schema(description = "Общее количество рабочих пространств", example = "50")
    private long totalWorkspaces;

    @Schema(description = "Количество доступных рабочих пространств", example = "30")
    private long availableWorkspaces;

    @Schema(description = "Общее количество бронирований", example = "200")
    private long totalBookings;

    @Schema(description = "Количество активных бронирований", example = "150")
    private long activeBookings;

    @Schema(description = "Общее количество гостевых доступов", example = "80")
    private long totalGuestAccess;

    @Schema(description = "Количество активных гостевых доступов", example = "25")
    private long activeGuestAccess;

    @Schema(description = "Общее количество парковочных мест", example = "40")
    private long totalParkingSpaces;

    @Schema(description = "Количество доступных парковочных мест", example = "20")
    private long availableParkingSpaces;

    @Schema(description = "Общее количество заказов поставки", example = "60")
    private long totalSupplyOrders;

    @Schema(description = "Количество ожидающих заказов поставки", example = "10")
    private long pendingSupplyOrders;

    @Schema(description = "Общее количество тикетов поддержки", example = "45")
    private long totalSupportTickets;

    @Schema(description = "Количество открытых тикетов поддержки", example = "15")
    private long openSupportTickets;
} 