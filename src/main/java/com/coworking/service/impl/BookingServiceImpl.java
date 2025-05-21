package com.coworking.service.impl;

import com.coworking.dto.BookingDTO;
import com.coworking.exception.BookingNotFoundException;
import com.coworking.exception.UserNotFoundException;
import com.coworking.exception.WorkspaceNotFoundException;
import com.coworking.mapper.BookingMapper;
import com.coworking.model.Booking;
import com.coworking.model.User;
import com.coworking.model.Workspace;
import com.coworking.repository.BookingRepository;
import com.coworking.repository.UserRepository;
import com.coworking.repository.WorkspaceRepository;
import com.coworking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDTO(savedBooking);
    }

    @Override
    public BookingDTO getBookingById(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));
        return bookingMapper.toDTO(booking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return  bookings.stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO updateBooking(UUID bookingId, BookingDTO bookingDTO) {
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingNotFoundException(bookingId));

        if (bookingDTO.getStartTime() != null) {
            existingBooking.setStartTime(bookingDTO.getStartTime());
        }
        if (bookingDTO.getEndTime() != null) {
            existingBooking.setEndTime(bookingDTO.getEndTime());
        }
        if (bookingDTO.getTotalPrice() != null) {
            existingBooking.setTotalPrice(bookingDTO.getTotalPrice());
        }
        if (bookingDTO.getStatus() != null) {
            existingBooking.setStatus(bookingDTO.getStatus());
        }
        if (bookingDTO.getWorkspaceId() != null) {
            Workspace workspace = workspaceRepository.findById(bookingDTO.getWorkspaceId())
                    .orElseThrow(() -> new WorkspaceNotFoundException(bookingDTO.getWorkspaceId()));
            existingBooking.setWorkspace(workspace);
        }
        if (bookingDTO.getUserId() != null) {
            User user = userRepository.findById(bookingDTO.getUserId())
                    .orElseThrow(() -> new UserNotFoundException(bookingDTO.getUserId()));
            existingBooking.setUser(user);
        }
        Booking updatedBooking = bookingRepository.save(existingBooking);

        return bookingMapper.toDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new BookingNotFoundException(bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }
}
