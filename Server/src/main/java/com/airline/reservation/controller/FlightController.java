package com.airline.reservation.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public FlightView add(@Valid @RequestBody FlightForm form) {
        return flightservice.add(form);
    }
}
