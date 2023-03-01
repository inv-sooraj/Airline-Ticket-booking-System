package com.airline.reservation.service;
import java.util.List;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.view.SeatView;
import java.util.Optional;

public interface SeatService {

    public SeatView updateSeat(Integer seatId, SeatForm form);
    public List<Seat> getSeatInfo(Integer flightId);
    public Integer getPrice(Integer seatId);
//    public SeatView updateSeat(Integer seatId, SeatForm form);

    Optional <Seat> getSeatById(Integer seatId);
    public List<Integer> getId(Integer flightId);
    public Integer getQuantity(Integer seatId);
   
}
