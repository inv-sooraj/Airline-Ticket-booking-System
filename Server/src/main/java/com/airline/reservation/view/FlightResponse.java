package com.airline.reservation.view;

public class FlightResponse {
    private Integer flightId;
    private String flightNumber;
    private String departure;
    private String destination;
    private Integer minSeatPrice;
  
    public Integer getFlightId() {
        return flightId;
    }
    public FlightResponse(Integer flightId, String flightNumber, String departure, String destination,
            Integer minSeatPrice) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.minSeatPrice = minSeatPrice;
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
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public Integer getMinSeatPrice() {
        return minSeatPrice;
    }
    public void setMinSeatPrice(Integer minSeatPrice) {
        this.minSeatPrice = minSeatPrice;
    }
}
