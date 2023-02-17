package com.airline.reservation.service;

import java.util.List;

import javax.validation.Valid;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightEditForm;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.FlightView;
import java.util.ArrayList;
import java.util.Optional;

public interface FlightService {

    Flight save(@Valid FlightForm form);

    List<Flight> findAll();

    List<Flight> findByCompany(Integer userId);

    Optional<Flight> findByFlightId(Integer flightId);
//    Optional<Flight> findByFlightIdAndSeat(Integer flightId,Integer seatId);

    public FlightView updateFlight(Integer flightId, FlightEditForm form);

    public void deleteFlightByIds(ArrayList<Integer> ids);

}
