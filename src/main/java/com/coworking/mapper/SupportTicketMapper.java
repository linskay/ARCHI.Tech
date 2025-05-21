package com.coworking.mapper;

import com.coworking.dto.SupportTicketDTO;
import com.coworking.exception.UserNotFoundException;
import com.coworking.model.SupportTicket;
import com.coworking.model.User;
import com.coworking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupportTicketMapper {
    private final UserRepository userRepository;

    public SupportTicketDTO toDTO(SupportTicket ticket) {
        if (ticket == null) {
            return null;
        }
        SupportTicketDTO supportTicketDTO = new SupportTicketDTO();
        supportTicketDTO.setTicketId(ticket.getTicketId());
        supportTicketDTO.setDescription(ticket.getDescription());
        supportTicketDTO.setUserId(ticket.getUser().getUserId());
        supportTicketDTO.setSubject(ticket.getSubject());
        supportTicketDTO.setPriority(ticket.getPriority());
        supportTicketDTO.setCreationDate(ticket.getCreationDate());
        supportTicketDTO.setStatus(ticket.getStatus());
        return supportTicketDTO;
    }

    public SupportTicket toEntity(SupportTicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }
        SupportTicket ticket = new SupportTicket();
        ticket.setTicketId(ticketDTO.getTicketId());

        User user = userRepository.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(ticketDTO.getUserId()));

        ticket.setUser(user);
        ticket.setSubject(ticketDTO.getSubject());
        ticket.setPriority(ticketDTO.getPriority());
        ticket.setCreationDate(ticketDTO.getCreationDate());
        ticket.setDescription(ticketDTO.getDescription());
        ticket.setSubject(ticketDTO.getSubject());
        ticket.setStatus(ticketDTO.getStatus());
        return ticket;
    }
}
