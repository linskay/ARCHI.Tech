package com.coworking.service;

import com.coworking.model.SupportTicket;
import java.util.List;
import java.util.UUID;

public interface SupportTicketService {
    List<SupportTicket> getAllSupportTickets();
    SupportTicket getSupportTicketById(UUID ticketId);
    SupportTicket createSupportTicket(SupportTicket supportTicket);
    SupportTicket updateSupportTicket(UUID ticketId, SupportTicket supportTicket);
    void deleteSupportTicket(UUID ticketId);
} 