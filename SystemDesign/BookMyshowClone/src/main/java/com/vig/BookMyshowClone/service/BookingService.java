package com.vig.BookMyshowClone.service;
import com.vig.BookMyshowClone.dto.BookingRequest;
import com.vig.BookMyshowClone.dto.BookingResponse;
import com.vig.BookMyshowClone.enums.SeatStatus;
import com.vig.BookMyshowClone.model.Booking;
import com.vig.BookMyshowClone.model.Seat;
import com.vig.BookMyshowClone.model.Show;
import com.vig.BookMyshowClone.model.User;
import com.vig.BookMyshowClone.repository.BookingRepository;
import com.vig.BookMyshowClone.repository.SeatRepository;
import com.vig.BookMyshowClone.repository.ShowRepository;
import com.vig.BookMyshowClone.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository,
                          ShowRepository showRepository,
                          SeatRepository seatRepository,
                          BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public BookingResponse bookSeats(BookingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found"));

        List<Long> seatIds = request.getSeatIds();

        // Lock seats with PESSIMISTIC_WRITE
        List<Seat> seats = seatRepository.findAllByIdForUpdate(seatIds);

        List<Seat> availableSeats = new ArrayList<>();

        for (Seat seat : seats) {
            if (!seat.getShow().getShowId().equals(show.getShowId())) {
                throw new RuntimeException("Seat does not belong to the given show");
            }
            if (seat.getStatus() == SeatStatus.BOOKED) {
                throw new RuntimeException("Seat already booked: " + seat.getSeatNumber());
            }
            seat.setStatus(SeatStatus.BOOKED);
            availableSeats.add(seat);
        }

        // Save updated seats
        seatRepository.saveAll(availableSeats);

        List<Long> bookedSeatIds = new ArrayList<>();
        for (Seat seat : availableSeats) {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setShow(show);
            booking.setSeat(seat);
            booking.setStatus("CONFIRMED");
            booking.setBookingTime(LocalDateTime.now());
            bookingRepository.save(booking);
            bookedSeatIds.add(seat.getSeatId());
        }

        return new BookingResponse("CONFIRMED", bookedSeatIds, bookedSeatIds.get(0));
    }
}
