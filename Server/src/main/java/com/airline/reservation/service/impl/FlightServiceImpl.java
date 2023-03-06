package com.airline.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.airline.reservation.entity.Flight;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.repository.SeatRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightView;
import com.airline.reservation.view.RandomFlightList;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    public Flight save(FlightForm form) {
        return flightRepository.save(new Flight(form));

    }

    public List<RandomFlightList> findRandom() {

        List<Flight> flight = flightRepository.findRandom();
        List<RandomFlightList> rlist = flight.stream().map(x -> new RandomFlightList(x.getFlightId(), x.getFlightNumber(), x.getDeparture(), x.getDepDateTime(), x.getDestination(), x.getDestDateTime())).collect(Collectors.toList());
        return rlist;
    }

    @Override
    public List<Flight> findByCompany(Integer userId) {
        return flightRepository.findByUserUserIdAndDeleteFlag(userId, Flight.DeleteFlag.ACTIVE.value);
    }

    @Override
    public Optional<Flight> findByFlightId(Integer flightId) {
        return flightRepository.findByFlightId(flightId);
    }

    @Override
    public List<Flight> searchResult(Integer pageNo, Integer pageSize, String sortBy, String sortDir, String departure, String destination, String depDateTime) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        System.out.println("date - " + depDateTime);
        Page<Flight> pagedResult = flightRepository.findByDepartureAndDestinationAndDepDateTime(paging, departure, destination, depDateTime);
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
