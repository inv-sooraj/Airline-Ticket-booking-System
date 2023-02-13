package com.airline.reservation.service;

import java.util.List;

import javax.validation.Valid;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.FlightView;

public interface FlightService {

    Flight save(@Valid FlightForm form);

    List<Flight> findAll();

    List<Flight> findByCompany(Integer userId);

    List<Flight> findByFlightId(Integer flightId);

}
