package com.airline.reservation.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Bookings;

public interface BookingRepository extends  JpaRepository<Bookings,Integer>
{

    List<Bookings> findByStatus(Byte status);
    // List<Bookings> findByStatus();
    // List<Bookings> findAll();
    Page<Bookings> findByFlightFlightNumber(Pageable paging,String flightNumber);
    Bookings findByBookingId(Integer bookingId);
}
