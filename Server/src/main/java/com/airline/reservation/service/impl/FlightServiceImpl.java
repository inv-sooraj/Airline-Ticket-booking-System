package com.airline.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.form.SeatForm;
import com.airline.reservation.form.UserForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.repository.SeatRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;
   

     // Add Flight
     @Override
     public FlightView add(FlightForm form) {
    List<SeatForm>ls=form.getSeats();
   String seatType =form.getSeats().get(0).getSeatType().toString();
//   Integer numOfSeat=form.getSeats().get(2).getNumber();
//   Integer price=form.getSeats().get(4).getPrice();
   System.out.println("Seat Type = "+seatType);
//    System.out.println("Seat Qty = "+numOfSeat);
//    System.out.println("Seat Price = "+price);
   
//    SeatForm sf= new SeatForm();
//    sf.setSeatType(seatType);
//    sf.setNumber(numOfSeat);
//    sf.setPrice(price);
//    List<SeatForm>list=new ArrayList<SeatForm>();
   return new FlightView(flightRepository.save(new Flight(form)));
     }
}
