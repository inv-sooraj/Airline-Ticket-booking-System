package com.airline.reservation.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.FlightView;
import com.airline.reservation.view.RandomFlightList;

public interface FlightService {

    Flight save(@Valid FlightForm form);

    List<Flight> findAll();

    List<Flight> findByCompany(Integer userId);

    List<Flight> findByFlightId(Integer flightId);
    List<RandomFlightList> findRandom();
    List<Flight> searchResult(Integer pageNo, Integer pageSize, String sortBy, String sortDir, String departure,String destination,String date);
}
