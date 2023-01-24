package com.airline.reservation.form;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FlightForm {
    @NotNull
    private Integer airplaneId;
    @NotNull @Size(min = 1,max = 30)
    private String flightNumber;
    @NotNull
    private String departure;
    @NotNull
    private Date depDateTime;
    @NotNull
    private String destination;
    @NotNull
    private Date destDateTime;

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
    
}
