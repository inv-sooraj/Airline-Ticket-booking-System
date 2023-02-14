package com.airline.reservation.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.RandomFlightList;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight save(@Valid FlightForm form);

    List<Flight> findByUserUserId(int i);

    @Query(value = "select * from flight order by RAND() LIMIT 3",nativeQuery=true)
    List<Flight> findRandom();

    List<Flight> findByFlightId(Integer flightId);

    // Page<Flight> findByDeparture(Pageable paging, String departure);

    Page<Flight> findByDepartureAndDestination(Pageable paging, String departure, String destination);

    @Query(value = "SELECT * FROM flight WHERE destination  like %?2% AND departure like %?1% AND dep_date_time like %?3%",nativeQuery = true)
    Page<Flight> findByDepartureAndDestinationAndDepDateTime(Pageable paging, String departure, String destination,String date);
}
 