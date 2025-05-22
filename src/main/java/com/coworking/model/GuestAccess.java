package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "guest_access")
public class GuestAccess {
    @Id
    @Column(name = "guest_access_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID guestAccessId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_guest_access_user")
    )
    private User user;
    
    @Column(name = "guest_name", nullable = false, length = 100)
    private String guestName;
    
    @Column(name = "guest_email", nullable = false)
    private String guestEmail;
    
    @Column(name = "access_start_time", nullable = false)
    private LocalDateTime accessStartTime;
    
    @Column(name = "access_end_time", nullable = false)
    private LocalDateTime accessEndTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private GuestAccessStatus status;
    
    public enum GuestAccessStatus {
        PENDING, APPROVED, REJECTED, ACTIVE, EXPIRED
    }
} 