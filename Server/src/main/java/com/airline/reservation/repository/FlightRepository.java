package com.airline.reservation.repository;

import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Flight;

public interface FlightRepository extends Repository<Flight, Integer> {

    Flight save(Flight flight);

}
