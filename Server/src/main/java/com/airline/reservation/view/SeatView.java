/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.view;

import com.airline.reservation.entity.Seat;

/**
 *
 * @author lakshmimohan
 */
public class SeatView {
    
    private final Integer seatId;
    private final String seatType;
    private final Integer number;
    private final Integer price;
    private final byte deleteFlag;

    public SeatView(Seat seat) {
         this.seatId=seat.getSeatId();
        this.seatType=seat.getSeatType();
        this.number=seat.getNumber();
        this.price=seat.getPrice();
        this.deleteFlag=seat.getDeleteFlag();
        
    }

    public Integer getSeatId() {
        return seatId;
    }

    public String getSeatType() {
        return seatType;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPrice() {
        return price;
    }

    public byte getDeleteFlag() {
        return deleteFlag;
    }
    
    
}
