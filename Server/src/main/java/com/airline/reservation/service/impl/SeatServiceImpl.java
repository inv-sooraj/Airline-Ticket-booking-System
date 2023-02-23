package com.airline.reservation.service.impl;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.repository.SeatRepository;
import org.springframework.stereotype.Service;

import com.airline.reservation.service.SeatService;
import com.airline.reservation.view.SeatView;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class SeatServiceImpl implements SeatService {
    
     @Autowired
    private SeatRepository seatRepository;

//    @Override
//    public SeatView updateSeat(Integer seatId, SeatForm form) {
//        
//        return seatRepository.findBySeatId(seatId)
//                .map((Seat) -> {
//                    return new SeatView(seatRepository.save(Seat.update(form)));
//                }).orElseThrow(NotFoundException::new);
//    }

    
   


    @Override
    public List<Seat> getSeatInfo(Integer flightId) {
        
       return seatRepository.findByFlightId(flightId);
    }





    @Override
    public Integer getPrice(Integer seatId) {
        return seatRepository.getPrice(seatId);
    }





    @Override
    public SeatView updateSeat(Integer seatId, SeatForm form) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSeat'");
    }





    @Override
    public List<Integer> getId(Integer flightId) {
        return seatRepository.getseatId(flightId);
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

