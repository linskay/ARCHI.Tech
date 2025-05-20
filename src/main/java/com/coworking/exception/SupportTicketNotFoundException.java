package com.coworking.exception;

import java.util.UUID;

public class SupportTicketNotFoundException extends RuntimeException {
    public SupportTicketNotFoundException(UUID ticketId) {
        super("Support ticket not found with ID:" + ticketId);
    }
}
