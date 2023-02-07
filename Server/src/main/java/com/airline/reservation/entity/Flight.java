package com.airline.reservation.entity;
<<<<<<< HEAD
import java.util.ArrayList;
=======

>>>>>>> f19bbfea1100554cad85a39b706bbea75814e096
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.airline.reservation.form.FlightForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "flight")
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Airplane airplane;
    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
    private List<Seat>seat;
    private String  flightNumber;
    private String  departure;
    private Date depDateTime;
    private String destination;
    private Date destDateTime;
    private byte deleteFlag;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    public Airplane getAirplane() {
        return airplane;
    }
    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
    public List<Seat> getSeat() {
        return seat;
    }
    public void setSeat(List<Seat> seat) {
        this.seat = seat;
    }
    public Flight() {
    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }
    public Flight(Integer flightId, Airplane airplaneId,List<Seat> seat ,String flightNumber, String departure, Date depDateTime, String destination,
    Date destDateTime) {
        this.flightId = flightId;
        this.airplane = airplaneId;
        this.seat=seat;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.depDateTime = depDateTime;
        this.destination = destination;
        this.destDateTime = destDateTime;
        this.deleteFlag = DeleteFlag.ACTIVE.value;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
        }
        public Flight(FlightForm form) {
            this.airplane = new Airplane(form.getAirplaneId());
            this.flightNumber = form.getFlightNumber();
            this.departure = form.getDeparture();
            this.depDateTime = form.getDepDateTime();
            this.destination = form.getDestination();
            this.destDateTime = form.getDepDateTime();
            this.deleteFlag = DeleteFlag.ACTIVE.value;
            Date dt = new Date();
            this.createDate = dt;
            this.updateDate = dt;
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
}
