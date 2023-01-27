package com.airline.reservation.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.airline.reservation.form.SeatForm;
import com.airline.reservation.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int seatId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;
    @Column(length=45,nullable = false)
    private String seatType;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private Integer price;
    @Column(columnDefinition="TINYINT",nullable = false)
    private Byte deleteFlag;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private   Date updateDate;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private  Date createDate;
    public int getSeatId() {
        return seatId;
    }
    public Seat(SeatForm form, Integer seatId, String string, Integer integer, Integer integer2) {
        this.seatId = seatId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public String getSeatType() {
        return seatType;
    }
    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Byte getDeleteFlag() {
        return deleteFlag;
    }
    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    } 
    
    public Seat(SeatForm form,Integer number,String seatType,Integer price,Byte deleteFlag) {
        this.flight=new Flight(form.getFlightId());
        this.number=number;
        this.price=price;
        this.seatType=seatType;
        this.deleteFlag=deleteFlag;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }
    public Seat(){

    }
    public Seat(Integer seatId) {
        this.seatId=seatId;
    }
}
