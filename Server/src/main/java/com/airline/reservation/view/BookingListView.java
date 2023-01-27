package com.airline.reservation.view;

import java.util.List;

import com.airline.reservation.entity.Bookings;

public class BookingListView {
   
    private Integer userId;
    private String flightNumber;
    private String departure;;
    private String destination;
    private Byte status;
    public BookingListView(Bookings bookings){
        this.userId=bookings.getUser().getUserId();
        this.flightNumber=bookings.getFlight().getFlightNumber();
        this.departure=bookings.getFlight().getDeparture();
        this.destination=bookings.getFlight().getDestination();
        this.status=bookings.getStatus();
    }
    public BookingListView(List<Bookings> findAll) {
        
    }
}
