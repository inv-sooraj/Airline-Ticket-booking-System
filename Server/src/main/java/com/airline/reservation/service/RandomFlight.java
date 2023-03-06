package com.airline.reservation.service;

import com.airline.reservation.entity.Flight;

public class RandomFlight {
    private Flight flight;
    private Integer price;
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    
}
