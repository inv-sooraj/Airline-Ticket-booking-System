package com.airline.reservation.controller;

import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.service.SeatService;
import com.airline.reservation.view.SeatView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seat")
public class SeatController {
    
    @Autowired
    private SeatService seatService;

@PutMapping("/{seatId}")
    public SeatView updateSeat(
            @PathVariable("seatId") Integer seatId,
            @Valid @RequestBody SeatForm form) {
        return seatService.updateSeat(seatId,form);
    }
}
