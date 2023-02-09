package com.airline.reservation.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.json.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.JoinColumn;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Flight {
    public static enum DeleteFlag {
        DELETE((byte) 0),
        ACTIVE((byte) 1);

        public final byte value;

        private DeleteFlag(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;
    private String flightNumber;
    private String departure;
    private Date depDateTime;
    private String destination;
    @Json.DateTimeFormat
    private Date destDateTime;

    
    private byte deleteFlag;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Airplane airplane;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(targetEntity = Seat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "cp_fk", referencedColumnName = "flight_id", nullable = false)
    private List<Seat> seats;

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    // public Airplane getAirplane() {
    // return airplane;
    // }
    // public void setAirplane(Airplane airplane) {
    // this.airplane = airplane;
    // }
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

    public byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    // public List<Seat> getSeats() {
    //     return seats;
    // }

    // public void setSeats(List<Seat> seats) {
    //     this.seats = seats;
    // }

 
    public Flight(FlightForm form) {
        this.flightNumber = form.getFlightNumber();
        this.departure = form.getDeparture();
        this.airplane=new Airplane(form.getAirplaneId());
        this.user=new User(form.getUserId());
        this.depDateTime = form.getDepDateTime();
        this.destination = form.getDestination();
        this.destDateTime = form.getDestDateTime();
        this.deleteFlag = 1;
        this.seats = form.getSeats();
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }

}
