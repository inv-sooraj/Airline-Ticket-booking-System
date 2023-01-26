package com.airline.reservation.service;

import java.util.Collection;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.view.FlightView;
import org.springframework.data.domain.Page;


public interface FlightService {

    FlightView add(Flight form);
    
    // flight list with company
    public Collection<Flight> getcompany(Integer userId);
    
    // Search
    Page<Flight>getFlightSearch(String keyword, Integer pageNo,Integer pageSize,String sortBy);
}
