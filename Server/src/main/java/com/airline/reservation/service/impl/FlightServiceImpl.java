package com.airline.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Add Company
    @Override
    public FlightView add(FlightForm form) {
        return new FlightView(flightRepository.save(new Flight(form)));
    }
}
