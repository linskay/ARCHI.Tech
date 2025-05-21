package com.coworking.mapper;

import com.coworking.dto.BookingDTO;
import com.coworking.exception.UserNotFoundException;
import com.coworking.exception.WorkspaceNotFoundException;
import com.coworking.model.Booking;
import com.coworking.model.User;
import com.coworking.model.Workspace;
import com.coworking.repository.UserRepository;
import com.coworking.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public BookingDTO toDTO(Booking booking) {
        if (booking == null) {
            return null;
        }
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setWorkspaceId(booking.getWorkspace().getWorkspaceId()); // Маппинг ID рабочего пространства
        bookingDTO.setUserId(booking.getUser().getUserId()); // Маппинг ID пользователя
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setTotalPrice(booking.getTotalPrice());
        bookingDTO.setStatus(booking.getStatus());
        return bookingDTO;
    }

    public Booking toEntity(BookingDTO bookingDTO) {
        if (bookingDTO == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setBookingId(bookingDTO.getBookingId());

        Workspace workspace = workspaceRepository.findById(bookingDTO.getWorkspaceId())
                .orElseThrow(() -> new WorkspaceNotFoundException(bookingDTO.getWorkspaceId())); // обработка ошибок
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException(bookingDTO.getUserId()));

        booking.setWorkspace(workspace);
        booking.setUser(user);
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setTotalPrice(bookingDTO.getTotalPrice());
        booking.setStatus(bookingDTO.getStatus());
        return booking;
    }
}
