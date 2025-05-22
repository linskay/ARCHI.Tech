package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "supply_orders")
public class SupplyOrder {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_supply_order_user")
    )
    private User user;

    @JsonIgnore
    @ElementCollection
    // @CollectionTable(name = "supply_order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "items", nullable = false, columnDefinition = "TEXT")
    // но он хочет теперь убрать List и оставить просто String или через отдельную таблицу предлагает
    private List<String> items;
    
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private SupplyOrderStatus status;
    
    public enum SupplyOrderStatus {
        PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELED
    }
} 