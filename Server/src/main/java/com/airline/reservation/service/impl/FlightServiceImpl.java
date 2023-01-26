package com.airline.reservation.service.impl;
import com.airline.reservation.security.util.SecurityUtil;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Flight;

import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;


@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    // Add Flight
    @Override
    public FlightView add(Flight form) {
        // Take values from constructor to entity
        return new FlightView(flightRepository.save(new Flight(form.getAirplane(),form.getFlightNumber(),
                form.getDeparture(), form.getDepDateTime(), form.getDestination(), form.getDestDateTime(),SecurityUtil.getCurrentUserId())));
    }
    

    //List of Flight with company
    @Override
    public Collection<Flight> getcompany(Integer userId) {
        return flightRepository.findAllByUserUserId(userId);
    } 

    // Search 
    @Override
    public Page<Flight>getFlightSearch(String keyword, Integer pageNo,Integer pageSize,String sortBy){
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    System.out.println(keyword);

    Page<Flight> pagedResult = flightRepository.findByName(keyword, paging);
    return pagedResult;
    }
}
