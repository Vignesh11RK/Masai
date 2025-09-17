package com.vig.BookMyshowClone.repository;


import com.vig.BookMyshowClone.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
