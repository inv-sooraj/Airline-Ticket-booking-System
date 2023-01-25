package com.airline.reservation.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.airline.reservation.form.FlightForm;

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
    @JoinColumn(name="airplane_id",referencedColumnName = "airplane_id")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Airplane airplane;
   @JoinColumn(name="user_id",referencedColumnName="user_id")
   @ManyToOne(optional = false,fetch = FetchType.LAZY)
   private User user;
    

    public Flight() {

    }

    public Flight(Integer flightId) {
        this.flightId = flightId;
    }

    
    public Flight(Airplane airplane,String flightNumber, String departure, Date depDateTime, String destination,
    Date destDateTime,Integer userId) {
        this.airplane=airplane;
        this.user = new User(userId);
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

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
