package com.airline.reservation.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.airline.reservation.entity.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Integer> {

    List<Bookings> findByDeleteFlag(Byte flag);

    Page<Bookings> findByFlightFlightNumber(Pageable paging, String flightNumber);

    Bookings findByBookingId(Integer bookingId);

    @Modifying
    @Query("update  Bookings p set p.deleteFlag = 0 where p.bookingId in(:integers)")
    void softDeleteAllIds(List<Integer> integers);

    List<Bookings> findByStatusAndDeleteFlag(Byte status,Byte deleteFlag);

    List<Bookings> findByUserUserIdAndDeleteFlag(Integer integer, Byte flag);

    Bookings findByBookingIdAndDeleteFlag(Integer bookingId, Byte flag);
    
        List<Bookings> findByFlightUserUserIdAndDeleteFlag(Integer userId, Byte flag);

        List<Bookings> findByStatusAndDeleteFlagAndFlightFlightId(Byte status, Byte deleteFlag, Integer flightId);

}
