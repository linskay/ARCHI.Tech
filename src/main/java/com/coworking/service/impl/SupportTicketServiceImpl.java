package com.coworking.service.impl;

import com.coworking.dto.SupportTicketDTO;
import com.coworking.exception.SupportTicketNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.mapper.SupportTicketMapper;
import com.coworking.model.SupportTicket;
import com.coworking.model.User;
import com.coworking.repository.SupportTicketRepository;
import com.coworking.service.SupportTicketService;
import com.coworking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupportTicketServiceImpl implements SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;
    private final UserRepository userRepository;
    private final SupportTicketMapper ticketMapper;

    @Override
    public List<SupportTicketDTO> getAllSupportTickets() {
        List<SupportTicket> tickets = supportTicketRepository.findAll();
        return tickets.stream()
                .map(ticketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupportTicketDTO getSupportTicketById(UUID ticketId) {
        SupportTicket ticket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new SupportTicketNotFoundException(ticketId));
        return ticketMapper.toDTO(ticket);
    }

    @Override
    public SupportTicketDTO createSupportTicket(SupportTicketDTO ticketDTO) {
        SupportTicket ticket = ticketMapper.toEntity(ticketDTO);
        SupportTicket savedTicket = supportTicketRepository.save(ticket);
        return ticketMapper.toDTO(savedTicket);
    }

    @Override
    public SupportTicketDTO updateSupportTicket(UUID ticketId, SupportTicketDTO ticketDTO) {
        SupportTicket existingTicket = supportTicketRepository.findById(ticketId)
                .orElseThrow(() -> new SupportTicketNotFoundException(ticketId));

        if (ticketDTO.getDescription() != null) {
            existingTicket.setDescription(ticketDTO.getDescription());
        }
        if (ticketDTO.getSubject() != null) {
            existingTicket.setSubject(ticketDTO.getSubject());
        }
        if (ticketDTO.getPriority() != null) {
            existingTicket.setPriority(ticketDTO.getPriority());
        }
        if (ticketDTO.getStatus() != null) {
            existingTicket.setStatus(ticketDTO.getStatus());
        }
        if (ticketDTO.getCreationDate() != null) {
            existingTicket.setCreationDate(ticketDTO.getCreationDate());
        }
        if (ticketDTO.getUserId() != null) {
            User user = userRepository.findById(ticketDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(ticketDTO.getUserId()));
            existingTicket.setUser(user);
        }
        SupportTicket updatedTicket = supportTicketRepository.save(existingTicket);

        return ticketMapper.toDTO(updatedTicket);
    }

    @Override
    public void deleteSupportTicket(UUID ticketId) {
        if (!supportTicketRepository.existsById(ticketId)) {
            throw new SupportTicketNotFoundException(ticketId);
        }
        supportTicketRepository.deleteById(ticketId);
    }
}