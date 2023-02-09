package com.airline.reservation.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.AddFlight;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;


    public FlightView save(FlightForm form) {
        flightRepository.save(new Flight(form));
        return new FlightView(new Flight(form));
    }
}
