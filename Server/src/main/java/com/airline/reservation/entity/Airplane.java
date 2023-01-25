/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.entity;

import com.airline.reservation.form.AirplaneForm;
import com.airline.reservation.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lakshmimohan
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Airplane {
    
   public static enum Status {
        DELETED((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airplaneId;
    @Column(nullable = false, length =30)
    private String airplaneName;
    @Column(nullable = false, length =30)
    private String modelNo;
    @Column(nullable = false)
    private Integer totalSeats;
      @Json.DateTimeFormat
    private Date createDate;
      @Json.DateTimeFormat
    private Date updateDate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    private byte status;


    public Airplane() {
    }

    public Airplane(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }
     public Airplane(AirplaneForm form,Integer userId,byte Status) {
         this.user = new User(userId);
         this.airplaneName=form.getAirplaneName();
         this.modelNo=form.getModelNo();
         this.totalSeats=form.getTotalSeats();
         Date dt = new Date();
         this.createDate = dt;
         this.updateDate = dt;
         this.status=Status;
    }
    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
    }

    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }
    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
    
    
}
