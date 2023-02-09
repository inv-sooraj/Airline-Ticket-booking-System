package com.airline.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightservice;

    // Add Flight
    @PostMapping
    public FlightView add(@RequestBody FlightForm form) {
        return flightservice.save(form);
    }

    //All Flights
    @GetMapping("/findAll")
    public List<Flight> findAll() {
        return flightservice.findAll();
    }

    @GetMapping("/findAll/{userId}")
    public List<Flight> findByCompany(@PathVariable("userId") Integer userId) {
        return flightservice.findByCompany(userId);
    }

}
