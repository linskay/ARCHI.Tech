package com.coworking.model;

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
public class GuestAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID guestAccessId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false, length = 100)
    private String guestName;
    
    @Column(nullable = false)
    private String guestEmail;
    
    @Column(nullable = false)
    private LocalDateTime accessStartTime;
    
    @Column(nullable = false)
    private LocalDateTime accessEndTime;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GuestAccessStatus status;
    
    public enum GuestAccessStatus {
        PENDING, APPROVED, REJECTED, ACTIVE, EXPIRED
    }
} 