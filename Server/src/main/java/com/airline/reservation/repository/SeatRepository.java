package com.airline.reservation.repository;
import com.airline.reservation.entity.Seat;
import antlr.collections.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Integer>{

}
    

