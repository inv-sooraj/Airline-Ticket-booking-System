package com.airline.reservation.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
 
 
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.entity.Flight;
import com.airline.reservation.form.FlightForm;
import com.airline.reservation.repository.FlightRepository;
import com.airline.reservation.service.FlightService;
import com.airline.reservation.view.FlightResponse;
import com.airline.reservation.view.RandomFlightList;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightservice;

@Autowired
private FlightRepository flightRepository;
   
    // Add Flight
    @PostMapping
    public  Flight add(@RequestBody FlightForm form) {
        return flightservice.save(form);
    }

    //All Flights
    @GetMapping("/findAll")
    public List<Flight> findAll() {
        return flightservice.findAll();
    }
    // get flight details of a paraticular company
    
    @GetMapping("/findAll/{userId}")
    public List<Flight> findByCompany(@PathVariable("userId") Integer userId) {
        return flightservice.findByCompany(userId);
    }

    //Flight Detail
    @GetMapping("/{flightId}")
    public List<Flight>findByFlightId(@PathVariable("flightId")Integer flightId){
        return flightservice.findByFlightId(flightId);
    }
    
    @GetMapping("/random")
    public List<RandomFlightList> findRandom() {
            return flightservice.findRandom();
    }
    //Search Flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllBookings(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam String departure, @RequestParam String destination, @RequestParam String depDateTime,    
            @RequestParam(defaultValue = "flight_id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        List<Flight> list = flightservice.searchResult(pageNo, pageSize, sortBy, sortDir, departure,destination,depDateTime);
        return new ResponseEntity<List<Flight>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @Autowired
JdbcTemplate jdbcTemplate;
@GetMapping("/getRandom")
public List<Map<String, Object>> getDataWithMinField() {
    String sql = "SELECT flight.*, " +
                 "(SELECT MIN(price) FROM seat WHERE flight.flight_id = seat.cp_fk) as min_price " +
                 "FROM flight  order by RAND() LIMIT 3";
    List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
    return data;
}

@GetMapping("/search")
public ResponseEntity<List<FlightResponse>> searchFlights(@RequestParam String departure, @RequestParam String destination, @RequestParam("depDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date depDateTime, Pageable pageable) {
  System.out.println(" DATE TIME = " + depDateTime);
    Page<Object[]> flightsPage = flightRepository.findFlightsWithMinSeatPrice(departure, destination, depDateTime, pageable);
    List<FlightResponse> flights = flightsPage.getContent().stream().map(objects -> new FlightResponse((Integer) objects[0], (String) objects[1], (String) objects[2], (String) objects[3], (Integer) objects[4])).collect(Collectors.toList());
    return ResponseEntity.ok(flights);
}

}
