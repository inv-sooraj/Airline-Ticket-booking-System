/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.form;

import java.util.Date;

/**
 *
 * @author lakshmimohan
 */
public class FlightEditForm {
     private Integer flightId;
    private String flightNumber;
    private Integer airplaneId;
    private Integer userId;
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

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
