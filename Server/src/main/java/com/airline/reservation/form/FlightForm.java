package com.airline.reservation.form;
import java.util.Date;
import java.util.List;
public class FlightForm {
    private Integer airplaneId;
    private String flightNumber;
    List<SeatForm> seats; 
    private String departure;
    private Date depDateTime;
    private String destination;
    public List<SeatForm> getSeats() {
        return seats;
    }
    public void setSeats(List<SeatForm> seats) {
        
        this.seats = seats;
    }
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
