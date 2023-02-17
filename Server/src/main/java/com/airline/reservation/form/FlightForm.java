package com.airline.reservation.form;

 
 
import java.util.Date;
import java.util.List;
import com.airline.reservation.json.*;
import com.airline.reservation.entity.Seat;
import com.airline.reservation.security.util.SecurityUtil;

public class FlightForm {
    
    private Integer flightId;
    private String flightNumber;
    private Integer airplaneId;
    private Integer userId;
    
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    private String departure;
   
    private  Date depDateTime;
    
    private List<Seat>seats;
    private String destination;
    public List<Seat> getSeats() {
        return seats;
    }
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
    private Date destDateTime;
    public Integer getAirplaneId() {
        return airplaneId;
    }
    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
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



    public  Date getDepDateTime() {
        return depDateTime;
    }



    public void setDepDateTime( Date depDateTime) {
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