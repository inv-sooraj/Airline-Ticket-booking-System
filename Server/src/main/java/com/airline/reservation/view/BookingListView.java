package com.airline.reservation.view;

import java.util.List;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.User;

public class BookingListView {
   
    private Integer userId;
    private String flightNumber;
    private String departure;;
    private String destination;
    private User user;
      private Flight flight;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    public String getDeparture() {
        return departure;
    }
    public void setDeparture(String departure) {
        this.departure = departure;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Byte getStatus() {
        return status;
    }
    public void setStatus(Byte status) {
        this.status = status;
    }
    private Byte status;
    public BookingListView(Bookings bookings){
        this.userId=bookings.getUser().getUserId();
         this.user=bookings.getUser();
        this.flight=bookings.getFlight();
        this.flightNumber=bookings.getFlight().getFlightNumber();
        this.departure=bookings.getFlight().getDeparture();
        this.destination=bookings.getFlight().getDestination();
        this.status=bookings.getStatus();
    }
    public BookingListView(List<Bookings> findAll) {
        
    }

    public User getUser() {
        return user;
    }

    public Flight getFlight() {
        return flight;
    }
    
}
