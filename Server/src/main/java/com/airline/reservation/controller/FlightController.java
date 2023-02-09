package com.airline.reservation.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.repository.SeatRepository;
import com.airline.reservation.service.AddFlight;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightservice;
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;
    // Add Flight
    @PostMapping
    public  FlightView add(@RequestBody FlightForm form) {
        return flightservice.save(form);
    }
}
