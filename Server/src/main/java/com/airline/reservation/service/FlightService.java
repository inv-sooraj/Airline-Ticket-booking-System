package com.airline.reservation.service;

import java.util.Collection;
import java.util.List;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.view.FlightView;



public interface FlightService {

    FlightView add(Flight form);
    
    // flight list with company
    public Collection<Flight> getcompany(Integer userId);
    
}
