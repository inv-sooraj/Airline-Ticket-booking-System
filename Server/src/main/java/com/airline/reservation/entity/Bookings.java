package com.airline.reservation.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Flight flight;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Seat seat;
    @Column(nullable = true)
    private String cancellation;
    @Column(nullable = false)
    private byte status = 2;
    @Column(nullable = false)
    private Byte deleteFlag = 1;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private Date updateDate;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private Date createDate;
    public Bookings(BookingForm form, String cancellation) {
        this.user = new User(form.getUserId());
        this.seat = new Seat(form.getSeatId());
        this.flight = new Flight(form.getFlightId());
        this.cancellation = cancellation;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
    }
    public Integer getBookingId() {
        return bookingId;
    }
    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    public String getCancellation() {
        return cancellation;
    }
    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }
    public byte getStatus() {
        return status;
    }
    public void setStatus(byte status) {
        this.status = status;
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
    public static enum Status {
        DELETE((byte) 0),
        ACTIVE((byte) 1),
        NOACTION((byte) 2);
        public final byte value;

        private Status(byte value) {
            this.value = value;
        }
    }
    
    public Bookings() {

    }
    public String toString() {
        return "Bookings [bookingId=" + bookingId + ", user=" + user + ", flight=" + flight + ", seat=" + seat
                + ", cancellation=" + cancellation + ", status=" + status + ", deleteFlag=" + deleteFlag
                + ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
    }
}
