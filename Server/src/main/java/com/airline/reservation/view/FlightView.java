package com.airline.reservation.view;

import java.util.Date;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.entity.Flight;

public class FlightView {
    
    private Integer flightId;
    private Airplane airplane;
    private String flightNumber;
    private String departure;
    private Date depDateTime;
    private String destination;
    private Date destDateTime;
    

    public FlightView(Flight flight){
        this.flightId = flight.getFlightId();
        this.airplane= flight.getAirplane();
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


    public Airplane getAirplane() {
        return airplane;
    }


    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    
}
