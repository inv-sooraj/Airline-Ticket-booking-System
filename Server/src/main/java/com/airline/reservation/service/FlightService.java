package com.airline.reservation.service;
import javax.validation.Valid;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.view.FlightView;
public interface FlightService {

    FlightView save(@Valid FlightForm form);

 
    
}
