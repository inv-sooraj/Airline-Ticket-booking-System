package com.airline.reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Bookings;

public interface BookingRepository extends  JpaRepository<Bookings,Integer>
{
    // List<Bookings> findAll();
    
}
