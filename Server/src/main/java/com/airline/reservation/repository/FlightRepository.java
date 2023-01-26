package com.airline.reservation.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Flight;

public interface FlightRepository extends Repository<Flight,Integer> {

   Flight save(Flight flight);
   
   // list of flights with company
   Collection<Flight> findAllByUserUserId(Integer userId);
   
   // Search
   @Query(value="SELECT * FROM flight WHERE flight_number LIKE %?1% ",nativeQuery = true)
     Page<Flight>findByName(String name,Pageable pageable);
}
