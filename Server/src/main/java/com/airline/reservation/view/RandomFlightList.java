package com.airline.reservation.view;

import java.util.Date;

public class RandomFlightList
{
    private Integer flightId;
    private String flightNumber;
    private String departure;
    private Date depDateTime;
    private String destination;
    private Date destDateTime;

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
public RandomFlightList(Integer flightId, String flightNumber, String departure, Date depDateTime,
            String destination, Date destDateTime) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.depDateTime = depDateTime;
        this.destination = destination;
        this.destDateTime = destDateTime;
    }
 public RandomFlightList(){}
    
}
