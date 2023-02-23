package com.airline.reservation.form;

import java.util.List;

public class BookingForm {
    private Integer bookingId;

    private Integer userId;

    private Integer flightId;

    private Integer seatId;

    private String cancellation;

    private byte status = 1;
    private List<BookingRequest> bookingRequests;

    public List<BookingRequest> getBookingRequests() {
        return bookingRequests;
    }

    public void setBookingRequests(List<BookingRequest> bookingRequests) {
        this.bookingRequests = bookingRequests;
    }
    public static class BookingRequest {
        private int userId;
        private int flightId;
        private int seatId;
        private int status=1;
        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getFlightId() {
            return flightId;
        }
        public void setFlightId(int flightId) {
            this.flightId = flightId;
        }
        public int getSeatId() {
            return seatId;
        }
        public void setSeatId(int seatId) {
            this.seatId = seatId;
        }
        public int getStatus() {
            return status;
        }
        public void setStatus(int status) {
            this.status = status;
        }

        // Constructor, getters and setters
    }   
    

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
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

}
