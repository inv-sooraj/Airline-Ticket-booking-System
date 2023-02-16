package com.airline.reservation.repository;

import java.util.List;
import javax.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    Flight save(@Valid FlightForm form);

    List<Flight> findByUserUserIdAndDeleteFlag(int i,byte flag);

    Optional<Flight> findByFlightId(Integer flightId);
    
//    Optional<Flight> findByFlightIdAndSeatSeatId(Integer flightId,Integer seatId);

    @Modifying
    @Transactional
    @Query("update  Flight p set p.deleteFlag = 0 where p.flightId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);
    List<Flight> findBydeleteFlag(byte flag);
}
