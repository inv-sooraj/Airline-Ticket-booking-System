package com.airline.reservation.view;

import java.util.Date;

import com.airline.reservation.entity.Flight;

public class FlightView {
    
    private Integer flightId;
    private Integer userId;
    private Integer airplaneId;
    private String flightNumber;
    private String departure;
    private Date depDateTime;
    private String destination;
    private Date destDateTime;
    

    public FlightView(Flight flight){
        this.flightId = flight.getFlightId();
        this.userId = flight.getUser().getUserId();
        this.airplaneId = flight.getAirplane().getAirplaneId();
        this.flightNumber = flight.getFlightNumber();
        this.departure = flight.getDeparture();
        this.depDateTime = flight.getDepDateTime();
        this.destination = flight.getDestination();
        this.destDateTime = flight.getDestDateTime();
    }


    public Integer getFlightId() {
        return flightId;
    }


    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }


    public Integer getAirplaneId() {
        return airplaneId;
    }


    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
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


    public Date getDepDateTime() {
        return depDateTime;
    }


    public void setDepDateTime(Date depDateTime) {
        this.depDateTime = depDateTime;
    }


    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }


    public Date getDestDateTime() {
        return destDateTime;
    }


    public void setDestDateTime(Date destDateTime) {
        this.destDateTime = destDateTime;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
}
