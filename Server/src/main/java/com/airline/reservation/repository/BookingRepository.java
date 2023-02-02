package com.airline.reservation.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Bookings;

public interface BookingRepository extends  JpaRepository<Bookings,Integer>
{

    List<Bookings> findByDeleteFlag(Byte flag);
    // List<Bookings> findByStatus();
    // List<Bookings> findAll();
    Page<Bookings> findByFlightFlightNumber(Pageable paging,String flightNumber);
    Bookings findByBookingId(Integer bookingId);
    
    // @Transactional
    @Modifying
    @Query("update  Bookings p set p.deleteFlag = 0 where p.bookingId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);
    List<Bookings> findByStatus(Byte status);
    List<Bookings> findByUserUserId(Integer integer);

    // List<Bookings> findByStatus();
    // List<Bookings> findAll();
    
}
