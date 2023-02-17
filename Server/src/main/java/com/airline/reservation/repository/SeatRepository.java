package com.airline.reservation.repository;
import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeatRepository extends JpaRepository<Seat,Integer>{

   
    Optional<Seat>  findBySeatId(Integer seatId);
    
     Seat save(SeatForm form);
     
//    @Query("select * from Seat s  where s.seatId in(:seats)")
//    Optional<Seat> findAllSeats(List<Seat> seats);
}
    

