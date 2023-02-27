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
    // @Query(value ="SELECT seat_id as seatId, seat_type as SeatType FROM seat  WHERE cp_fk=?1", nativeQuery = true)
    // List<Object[]> findAllSeatIdAndType(Integer flightId);
    @Query(value ="SELECT * FROM seat  WHERE cp_fk=?1", nativeQuery = true)
    List<Seat> findByFlightId(Integer flightId);
    @Query(value ="SELECT price from seat WHERE seat_id=?1",nativeQuery=true)
    Integer getPrice(Integer seatId);
    
//    @Query("select * from Seat s  where s.seatId in(:seats)")
//    Optional<Seat> findAllSeats(List<Seat> seats);
  

}
    

