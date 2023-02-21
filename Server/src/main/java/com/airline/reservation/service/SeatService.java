package com.airline.reservation.service;
import java.util.List;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.view.SeatView;

public interface SeatService {

    public SeatView updateSeat(Integer seatId, SeatForm form);
    public List<Seat> getSeatInfo(Integer flightId);
    public Integer getPrice(Integer seatId);
   
}
