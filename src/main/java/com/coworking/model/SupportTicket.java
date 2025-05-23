package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Модель тикета поддержки")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "support_tickets")
public class SupportTicket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор тикета", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID ticketId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_support_ticket_user")
    )
    @Schema(description = "Пользователь, создавший тикет", required = true)
    private User user;
    
    @NotBlank(message = "Тема не может быть пустой")
    @Size(min = 3, max = 200, message = "Тема должна содержать от 3 до 200 символов")
    @Column(name = "subject", nullable = false, length = 200)
    @Schema(description = "Тема тикета", example = "Проблема с доступом к рабочему пространству", required = true)
    private String subject;
    
    @NotBlank(message = "Описание не может быть пустым")
    @Size(min = 10, max = 2000, message = "Описание должно содержать от 10 до 2000 символов")
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    @Schema(description = "Подробное описание проблемы", example = "Не могу получить доступ к забронированному рабочему пространству", required = true)
    private String description;

    @NotNull(message = "Приоритет не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, length = 10)
    @Schema(description = "Приоритет тикета", example = "MEDIUM", required = true)
    private TicketPriority priority;

    @NotNull(message = "Статус не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Schema(description = "Статус тикета", example = "OPEN", required = true)
    private TicketStatus status;

    @NotNull(message = "Дата создания не может быть пустой")
    @Column(name = "creation_date", nullable = false)
    @Schema(description = "Дата и время создания тикета", example = "2024-03-20T10:00:00", required = true)
    private LocalDateTime creationDate;

    @Schema(description = "Приоритеты тикетов")
    public enum TicketPriority {
        @Schema(description = "Низкий приоритет")
        LOW,
        @Schema(description = "Средний приоритет")
        MEDIUM,
        @Schema(description = "Высокий приоритет")
        HIGH
    }

    @Schema(description = "Статусы тикетов")
    public enum TicketStatus {
        @Schema(description = "Открыт")
        OPEN,
        @Schema(description = "В работе")
        IN_PROGRESS,
        @Schema(description = "Решен")
        RESOLVED,
        @Schema(description = "Закрыт")
        CLOSED
    }
} 