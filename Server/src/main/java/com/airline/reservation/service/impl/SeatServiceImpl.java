package com.airline.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.repository.SeatRepository;
import com.airline.reservation.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public void addSeat(SeatForm form) {
        seatRepository.save(new Seat(form, form.getFlightId(), form.getSeatType(), form.getNumber(), form.getDeleteFlag()));
    }

    @Override
    public List<Seat> list() {
        return seatRepository.findAll();
    }
}
