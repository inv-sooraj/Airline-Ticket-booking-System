package com.airline.reservation.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.service.FlightService;

public class FlightServiceImpl implements FlightService {
@Autowired
private FlightService flightService;
    @Override
    public Collection<Flight> findRandom() {
        // TODO Auto-generated method stub
        return flightService.findRandom();
    }

  
    
}
