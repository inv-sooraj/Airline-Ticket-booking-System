package com.airline.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.repository.SeatRepository;

public class SeatServiceImpl {
   @Autowired
   private SeatRepository seatRepository;
    public void addSeat(SeatForm form) {
        seatRepository.save(new Seat(form,form.getFlightId(),form.getSeatType(),form.getNumber(), form.getDeleteFlag()));
    }
}
