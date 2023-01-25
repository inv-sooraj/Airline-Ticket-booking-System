package com.airline.reservation.service.impl;
import com.airline.reservation.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Flight;

import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Add Company
    @Override
    public FlightView add(Flight form) {
        // Take values from constructor to entity
        return new FlightView(flightRepository.save(new Flight(form.getAirplane(),form.getFlightNumber(),
                form.getDeparture(), form.getDepDateTime(), form.getDestination(), form.getDestDateTime(),SecurityUtil.getCurrentUserId())));
    }
    
}
