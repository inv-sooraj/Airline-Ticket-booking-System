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
import java.util.ArrayList;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return flightservice.findAll() ;
    }
    // get flight details of a paraticular company
    
    @GetMapping("/findAll/{userId}")
    public List<Flight> findByCompany(@PathVariable("userId") Integer userId) {
        return flightservice.findByCompany(userId);
    }

    //Flight Detail
    @GetMapping("/{flightId}")
    public Optional<Flight>findByFlightId(@PathVariable("flightId")Integer flightId){
        return flightservice.findByFlightId(flightId);
    }
    
    //update flight details of a particular flight
    
    @PutMapping("/{flightId}")
    public FlightView updateFlightById(
            @PathVariable("flightId") Integer flightId,
            @Valid @RequestBody FlightForm form) {
        return flightservice.updateFlight(flightId,form);
    }
    
    //delete flights
    @DeleteMapping
    public void deleteFlights(@RequestParam("ids") ArrayList<Integer> ids) {
        System.out.println("deleting");
        flightservice.deleteFlightByIds(ids);
    }
}
