package com.airline.reservation.service;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.FlightView;
public interface FlightService {
    FlightView add(FlightForm form);
}
