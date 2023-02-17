package com.airline.reservation.entity;

import com.airline.reservation.form.SeatForm;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.airline.reservation.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;
   
    @Column(length = 45, nullable = true)
    private String seatType;
    @Column(nullable = true)
    private Integer number;
    @Column(nullable = true)
    private Integer price;
    @Column(columnDefinition = "TINYINT", nullable = true)
    private Byte deleteFlag;
    @Column(nullable = true)
    @Json.DateTimeFormat
    private Date updateDate;
    public int getSeatId() {
        return seatId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
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


    @Column(nullable = true)
    @Json.DateTimeFormat
    private Date createDate;


    public Seat(Integer seatId) {
        this.seatId=seatId;
    }
    public Seat(){
        this.number=getNumber();
    this.price=getPrice();
    this.deleteFlag=1;
    Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }
    
    public Seat update(SeatForm form){
       this.seatType=form.getSeatType();
       this.number=form.getNumber();
       this.price=form.getPrice();
        Date dt = new Date();
        this.updateDate = dt;
        this.deleteFlag = 1;
        return this;
    }
}
