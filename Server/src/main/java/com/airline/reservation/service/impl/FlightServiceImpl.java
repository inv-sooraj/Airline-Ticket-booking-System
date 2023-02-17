package com.airline.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.FlightEditForm;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;
import java.util.ArrayList;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;



    public Flight save(FlightForm form) {
        return flightRepository.save(new Flight(form));
       
    }

    @Override
    public List<Flight> findByCompany(Integer userId) {
        return flightRepository.findByUserUserIdAndDeleteFlag(userId,Flight.DeleteFlag.ACTIVE.value);
    }


    @Override
    public Optional<Flight> findByFlightId(Integer flightId) {
       return flightRepository.findByFlightId(flightId);
    }

    @Override
    @Transactional
    public FlightView updateFlight(Integer flightId, FlightForm form) {
        
         return flightRepository.findByFlightId(flightId)
                .map((Flight) -> {
                    return new FlightView(flightRepository.save(Flight.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public void deleteFlightByIds(ArrayList<Integer> ids) {
        
         flightRepository.softDeleteAllIds(ids);
    }

    @Override
    public List<Flight> findAll() {
       return flightRepository.findBydeleteFlag(Flight.DeleteFlag.ACTIVE.value);
    }


}
