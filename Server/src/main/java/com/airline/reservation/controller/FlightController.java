package com.airline.reservation.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightservice;

    // Add Flight
    @PostMapping
    public FlightView add(@Valid @RequestBody Flight form){
        return flightservice.add(form);
    }

    // List in company vise
    @GetMapping("/companyname/{userId}")
    public Collection<Flight>get(@PathVariable("userId") Integer userId){
        return flightservice.getcompany(userId);
    }
   
    // Search
    @GetMapping("/search/pagenateds")
    public ResponseEntity<Page<Flight>>getFlight(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "user_id") String sortBy) {
        System.out.println("paage size" + pageSize);
        Page<Flight> list = flightservice.getFlightSearch(keyword, pageNo - 1, pageSize, sortBy);
        return new ResponseEntity<Page<Flight>>(list, new HttpHeaders(),
                HttpStatus.OK);

    }
}
