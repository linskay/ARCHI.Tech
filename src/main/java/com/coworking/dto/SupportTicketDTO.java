package com.coworking.dto;

import com.coworking.model.SupportTicket;
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
    private UUID ticketId;

    private UUID userId;

    private String subject;

    private String description;

    private SupportTicket.TicketPriority priority;

    private SupportTicket.TicketStatus status;

    private LocalDateTime creationDate;
}
