package com.airline.reservation.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    private Integer airplaneId;
    private String flightNumber;
    private String departure;
    @Json.DateTimeFormat
    private Date depDateTime;
    private String destination;
    @Json.DateTimeFormat
    private Date destDateTime;
    private byte deleteFlag;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Flight() {
    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    public Flight(Integer flightId, Integer airplaneId, String flightNumber, String departure, Date depDateTime, String destination,
            Date destDateTime) {
        this.flightId = flightId;
        this.airplaneId = airplaneId;
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
        this.airplaneId = form.getAirplaneId();
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

    public Integer getAirplaneId() {
        return airplaneId;
    }

    public void setAirplaneId(Integer airplaneId) {
        this.airplaneId = airplaneId;
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
