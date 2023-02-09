package com.airline.reservation.service.impl;

import java.util.List;

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


    public Flight save(FlightForm form) {
        return flightRepository.save(new Flight(form));
       
    }


    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }


    @Override
    public List<Flight> findByCompany(Integer userId) {
      return flightRepository.findByUserUserId(userId);
    }


    @Override
    public List<Flight> findByFlightId(Integer flightId) {
       return flightRepository.findByFlightId(flightId);
    }


}
