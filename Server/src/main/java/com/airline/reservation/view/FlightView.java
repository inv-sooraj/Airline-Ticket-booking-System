package com.airline.reservation.view;


import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.Seat;
import com.airline.reservation.entity.User;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.json.Json;

 
 
import java.util.Date;
import java.util.List;

public class FlightView {

    private  Integer flightId;
    private String flightNumber;
    private String departure;
     @Json.DateTimeFormat
    private  Date depDateTime;
    private String destination;
    @Json.DateTimeFormat
    private Date destDateTime;
    private User user;
    private List<Seat> seats;
   
    public FlightView(Flight flight) {
        
        this.flightId=flight.getFlightId();
        this.flightNumber=flight.getFlightNumber();
        this.departure=flight.getDeparture();
        this.depDateTime=flight.getDepDateTime();
        this.destination=flight.getDestination();
        this.destDateTime=flight.getDestDateTime();
        this.user=flight.getUser();
        this.seats=flight.getSeats();
        
    }

    public FlightView(FlightForm flightForm) {
    }

    public Integer getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public  Date getDepDateTime() {
        return depDateTime;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDestDateTime() {
        return destDateTime;
    }

//    public User getUser() {
//        return user;
//    }

    public List<Seat> getSeats() {
        return seats;
    }

    
    
}
