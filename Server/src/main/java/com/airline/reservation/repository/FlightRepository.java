package com.airline.reservation.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Flight;

public interface FlightRepository extends Repository<Flight,Integer> {

   Flight save(Flight flight);
   
   // list of flights with company
   // @Query(value = "SELECT * FROM flight where user_id=:userId",nativeQuery = true)
   Collection<Flight> findAllByUserUserId(Integer userId);
    
}
