package com.airline.reservation.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.service.RandomFlight;
import com.airline.reservation.view.FlightView;
import com.airline.reservation.view.RandomFlightList;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    public Flight save(FlightForm form) {
        return flightRepository.save(new Flight(form));
       
    }
    public List<RandomFlightList> findRandom() {
        
        List<Flight> flight =  flightRepository.findRandom();
        List<RandomFlightList> rlist = flight.stream().map(x -> new RandomFlightList(x.getFlightId(),x.getFlightNumber(),x.getDeparture(),x.getDepDateTime(),x.getDestination(),x.getDestDateTime())).collect(Collectors.toList());
        return rlist;
    }
    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findByCompany(Integer userId) {
        return flightRepository.findByUserUserId(userId);
    }


    @Override
    public List<Flight> findByFlightId(Integer flightId) {
       return flightRepository.findByFlightId(flightId);
    }
    @Override
    public List<Flight> searchResult(Integer pageNo, Integer pageSize, String sortBy, String sortDir, String departure,String destination,String date) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        System.out.println("date - "+ date);
        Page<Flight> pagedResult = flightRepository.findByDepartureAndDestinationAndDepDateTime(paging, departure,destination,date);
        if (departure == "") {
            Page<Flight> AllpagedResult = flightRepository.findAll(paging);
            if (AllpagedResult.hasContent()) {
                return AllpagedResult.getContent();
            } else {
                return new ArrayList<Flight>();
            }
        }
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Flight>();
        }
    }

}
