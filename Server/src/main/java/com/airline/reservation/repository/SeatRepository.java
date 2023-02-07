package com.airline.reservation.repository;
import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SeatRepository extends CrudRepository<Seat,Integer>{
        
}
    

