package com.airline.reservation.view;

import java.util.Date;
import java.util.List;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.Seat;
import com.airline.reservation.form.FlightForm;

public class FlightView {

        public  Integer flightId;
   
    public FlightView(Flight flight) {
        flightId=flight.getFlightId();
    }

    public FlightView(FlightForm flightForm) {
    }
}
