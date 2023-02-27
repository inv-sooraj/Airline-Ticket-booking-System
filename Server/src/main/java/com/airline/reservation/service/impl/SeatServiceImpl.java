package com.airline.reservation.service.impl;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.repository.SeatRepository;
import org.springframework.stereotype.Service;

import com.airline.reservation.service.SeatService;
import com.airline.reservation.view.SeatView;

import java.util.List;
import java.util.Optional;

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

    
   


    @Override
    public List<Seat> getSeatInfo(Integer flightId) {
        
       return seatRepository.findByFlightId(flightId);
    }





    @Override
    public Integer getPrice(Integer seatId) {
        return seatRepository.getPrice(seatId);
    }

    @Override
    public Optional<Seat> getSeatById(Integer seatId) {
        
        return seatRepository.findBySeatId(seatId);
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

