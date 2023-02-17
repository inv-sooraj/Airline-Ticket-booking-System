package com.airline.reservation.form;

import java.util.Date;

public class FlightSearchForm {
    private String departure;
    private String destination;
    private Date depDateTime;
    public String getDeparture() {
        return departure;
    }
    public FlightSearchForm(String departure, String destination, Date depDateTime) {
        this.departure = departure;
        this.destination = destination;
        this.depDateTime = depDateTime;
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
    public Date getDepDateTime() {
        return depDateTime;
    }
    public void setDepDateTime(Date depDateTime) {
        this.depDateTime = depDateTime;
    }
}
