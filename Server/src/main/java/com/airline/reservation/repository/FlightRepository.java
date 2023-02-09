package com.airline.reservation.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.FlightView;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    Flight save(@Valid FlightForm form);

    List<Flight> findByUserUserId(int i);
}
