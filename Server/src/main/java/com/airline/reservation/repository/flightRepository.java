package com.airline.reservation.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import com.airline.reservation.entity.Flight;

public interface flightRepository {
    @Query(value = "select * from flight order by RAND() LIMIT 3",nativeQuery=true)
    Collection<Flight>findRandom();
}
