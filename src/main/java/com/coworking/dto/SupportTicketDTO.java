package com.coworking.dto;

import com.coworking.model.SupportTicket;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicketDTO {
    @NotNull(message = "Идентификатор тикета обязателен")
    private UUID ticketId;

    @NotNull(message = "Идентификатор пользователя обязателен")
    private UUID userId;

    @NotBlank(message = "Тема обращения обязательна")
    @Size(max = 200, message = "Тема обращения не должна превышать 200 символов")
    private String subject;

    @NotBlank(message = "Описание обращения обязательно")
    private String description;

    @NotNull(message = "Приоритет обращения обязателен")
    private SupportTicket.TicketPriority priority;

    @NotNull(message = "Статус обращения обязателен")
    private SupportTicket.TicketStatus status;

    @NotNull(message = "Дата создания обращения обязательна")
    @PastOrPresent(message = "Дата создания не может быть в будущем")
    private LocalDateTime creationDate;
}
