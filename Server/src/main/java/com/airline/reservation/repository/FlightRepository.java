package com.airline.reservation.repository;

 
 
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.RandomFlightList;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight save(@Valid FlightForm form);

    List<Flight> findByUserUserIdAndDeleteFlag(int i, byte flag);

    @Query(value = "select * from flight order by RAND() LIMIT 3",nativeQuery=true)
    List<Flight> findRandom();

    // List<Flight> findByFlightId(Integer flightId);

    // Page<Flight> findByDeparture(Pageable paging, String departure);

    Page<Flight> findByDepartureAndDestination(Pageable paging, String departure, String destination);

    @Query(value = "SELECT * FROM flight WHERE destination  like %?2% AND departure like %?1% AND dep_date_time like %?3%",nativeQuery = true)
    Page<Flight> findByDepartureAndDestinationAndDepDateTime(Pageable paging, String departure, String destination,String depDateTime);
    @Query("SELECT f.flightId, f.flightNumber, f.departure, f.destination, MIN(s.price) AS minSeatPrice " +
       "FROM Flight f JOIN f.seats s " +
       "WHERE f.departure LIKE :departure AND f.destination LIKE:destination AND DATE(f.depDateTime) LIKE DATE(:depDateTime)" +
       "GROUP BY f.flightId")
Page<Object[]> findFlightsWithMinSeatPrice(@Param("departure") String departure, @Param("destination") String destination, @Param("depDateTime")  Date depDateTime, Pageable pageable);


    Optional<Flight> findByFlightId(Integer flightId);

    @Modifying
    @Transactional
    @Query("update  Flight p set p.deleteFlag = 0 where p.flightId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);

    List<Flight> findBydeleteFlag(byte flag);
}
 