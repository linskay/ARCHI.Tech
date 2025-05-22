package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookingId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "workspace_id",
            referencedColumnName = "workspace_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_booking_workspace")
    )
    private Workspace workspace;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_booking_user")
    )
    private User user;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private BookingStatus status;

    public enum BookingStatus {
        PENDING, CONFIRMED, CANCELED, COMPLETED
    }
} 