package com.airline.reservation.repository;

import java.util.List;
import javax.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight save(@Valid FlightForm form);

    List<Flight> findByUserUserId(int i);

    List<Flight> findByFlightId(Integer flightId);
}
