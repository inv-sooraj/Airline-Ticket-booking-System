package com.airline.reservation.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.RandomFlightList;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightservice;


   
    // Add Flight
    @PostMapping
    public  Flight add(@RequestBody FlightForm form) {
        return flightservice.save(form);
    }

    //All Flights
    @GetMapping("/findAll")
    public List<Flight> findAll() {
        return flightservice.findAll();
    }
    // get flight details of a paraticular company
    
    @GetMapping("/findAll/{userId}")
    public List<Flight> findByCompany(@PathVariable("userId") Integer userId) {
        return flightservice.findByCompany(userId);
    }

    //Flight Detail
    @GetMapping("/{flightId}")
    public List<Flight>findByFlightId(@PathVariable("flightId")Integer flightId){
        return flightservice.findByFlightId(flightId);
    }
    
    @GetMapping("/random")
    public List<RandomFlightList> findRandom() {
            return flightservice.findRandom();
    }
    //Search Flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllBookings(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam String departure, @RequestParam String destination, @RequestParam String date,    
            @RequestParam(defaultValue = "flight_id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        List<Flight> list = flightservice.searchResult(pageNo, pageSize, sortBy, sortDir, departure,destination,date);
        return new ResponseEntity<List<Flight>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
