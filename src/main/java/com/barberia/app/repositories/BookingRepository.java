package com.barberia.app.repositories;

import com.barberia.app.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStatusAndChosenTimeSlotAfter(String status, LocalDateTime yesterday);

    List<Booking> findAllByStatusAndChosenTimeSlotAfterOrderByChosenTimeSlotAsc(String status, LocalDateTime yesterday);

    List<Booking> findByCustomerIdAndChosenTimeSlotAfter(long customerId, LocalDateTime yesterday);

}
