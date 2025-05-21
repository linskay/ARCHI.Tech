package com.coworking.service.impl;

import com.coworking.exception.SupportTicketNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.model.SupportTicket;
import com.coworking.model.User;
import com.coworking.repository.SupportTicketRepository;
import com.coworking.service.SupportTicketService;
import com.coworking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SupportTicketServiceImpl implements SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;
    private final UserRepository userRepository;

    @Override
    public List<SupportTicket> getAllSupportTickets() {
        return supportTicketRepository.findAll();
    }

    @Override
    public SupportTicket getSupportTicketById(UUID ticketId) {
        return supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new SupportTicketNotFoundException(ticketId));
    }

    @Override
    public SupportTicket createSupportTicket(SupportTicket supportTicket) {
        UUID userId = supportTicket.getUser().getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        supportTicket.setUser(user);
        supportTicket.setSubject(supportTicket.getSubject());
        supportTicket.setDescription(supportTicket.getDescription());
        supportTicket.setCreationDate(LocalDateTime.now());
        supportTicket.setStatus(SupportTicket.TicketStatus.OPEN);
        return supportTicketRepository.save(supportTicket);
    }

    @Override
    public SupportTicket updateSupportTicket(UUID ticketId, SupportTicket supportTicket) {
        SupportTicket existingTicket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new SupportTicketNotFoundException(ticketId));

        if (supportTicket.getUser() != null) {
            UUID userId = supportTicket.getUser().getUserId();
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(userId));
            existingTicket.setUser(user);
        }

        existingTicket.setSubject(supportTicket.getSubject());
        existingTicket.setDescription(supportTicket.getDescription());
        existingTicket.setPriority(supportTicket.getPriority());
        existingTicket.setStatus(supportTicket.getStatus());

        return supportTicketRepository.save(existingTicket);
    }

    @Override
    public void deleteSupportTicket(UUID ticketId) {
        if (!supportTicketRepository.existsById(ticketId)) {
            throw new SupportTicketNotFoundException(ticketId);
        }
        supportTicketRepository.deleteById(ticketId);
    }
}