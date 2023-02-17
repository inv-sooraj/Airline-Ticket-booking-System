package com.airline.reservation.service.impl;

import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.repository.SeatRepository;
import org.springframework.stereotype.Service;

import com.airline.reservation.service.SeatService;
import com.airline.reservation.view.SeatView;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class SeatServiceImpl implements SeatService {
    
     @Autowired
    private SeatRepository seatRepository;

    @Override
    public SeatView updateSeat(Integer seatId, SeatForm form) {
        
        return seatRepository.findBySeatId(seatId)
                .map((Seat) -> {
                    return new SeatView(seatRepository.save(Seat.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    }

    // @Autowired
    // private SeatRepository seatRepository;

    // public void addSeat(SeatForm form) {
    //     seatRepository.save(new Seat(form, form.getFlightId(), form.getSeatType(), form.getNumber(), form.getDeleteFlag()));
    // }

    // @Override
    // public List<Seat> list() {
    //     return seatRepository.findAll();
    // }

