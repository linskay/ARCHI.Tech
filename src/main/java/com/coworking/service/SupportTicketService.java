package com.coworking.service;

import com.coworking.dto.SupportTicketDTO;
import com.coworking.model.SupportTicket;
import java.util.List;
import java.util.UUID;

public interface SupportTicketService {
    List<SupportTicketDTO> getAllSupportTickets();
    SupportTicketDTO getSupportTicketById(UUID ticketId);
    SupportTicketDTO createSupportTicket(SupportTicketDTO supportTicketDTO);
    SupportTicketDTO updateSupportTicket(UUID ticketId, SupportTicketDTO supportTicketDTO);
    void deleteSupportTicket(UUID ticketId);
} 