package com.coworking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Модель гостевого доступа")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "guest_access")
public class GuestAccess {
    @Id
    @Column(name = "guest_access_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Уникальный идентификатор гостевого доступа", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID guestAccessId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_guest_access_user")
    )
    @Schema(description = "Пользователь, пригласивший гостя", required = true)
    private User user;
    
    @NotBlank(message = "Имя гостя не может быть пустым")
    @Size(min = 2, max = 100, message = "Имя гостя должно содержать от 2 до 100 символов")
    @Column(name = "guest_name", nullable = false, length = 100)
    @Schema(description = "Имя гостя", example = "Иван Иванов", required = true)
    private String guestName;
    
    @NotBlank(message = "Email гостя не может быть пустым")
    @Email(message = "Некорректный формат email")
    @Column(name = "guest_email", nullable = false)
    @Schema(description = "Email гостя", example = "guest@example.com", required = true)
    private String guestEmail;
    
    @NotNull(message = "Время начала доступа не может быть пустым")
    @Future(message = "Время начала доступа должно быть в будущем")
    @Column(name = "access_start_time", nullable = false)
    @Schema(description = "Время начала доступа", example = "2024-03-20T10:00:00", required = true)
    private LocalDateTime accessStartTime;
    
    @NotNull(message = "Время окончания доступа не может быть пустым")
    @Future(message = "Время окончания доступа должно быть в будущем")
    @Column(name = "access_end_time", nullable = false)
    @Schema(description = "Время окончания доступа", example = "2024-03-20T18:00:00", required = true)
    private LocalDateTime accessEndTime;
    
    @NotNull(message = "Статус не может быть пустым")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Schema(description = "Статус гостевого доступа", example = "PENDING", required = true)
    private GuestAccessStatus status;
    
    @Schema(description = "Статусы гостевого доступа")
    public enum GuestAccessStatus {
        @Schema(description = "Ожидает подтверждения")
        PENDING,
        @Schema(description = "Подтвержден")
        APPROVED,
        @Schema(description = "Отклонен")
        REJECTED,
        @Schema(description = "Активен")
        ACTIVE,
        @Schema(description = "Истек")
        EXPIRED
    }
} 