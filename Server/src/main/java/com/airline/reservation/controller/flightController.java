package com.airline.reservation.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.RandomFlightListView;

public class flightController {
    @Autowired
    private FlightService flightService;
    @GetMapping("/sorted/name")
    public Collection<Flight> findRandom() {
            return flightService.findRandom();
    }
}
