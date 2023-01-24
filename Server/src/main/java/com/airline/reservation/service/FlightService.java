package com.airline.reservation.service;

import java.util.Collection;

import com.airline.reservation.entity.Flight;

public interface FlightService {
    
    Collection<Flight> findRandom();
    
}
