package com.airline.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.service.BookingService;
import com.airline.reservation.service.CSVService;
import com.airline.reservation.view.BookingListView;
@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    CSVService fileService;
    @GetMapping("/list")
    public List<Bookings> list(){
    return bookingService.list();
    }
    // @GetMapping("/list2")
    // public List<BookingListView> bookingList()
    // {
    //    return bookingService.bookingList();
        
    // }
    @PostMapping("/addBooking")
    public void addJob(@RequestBody BookingForm form)
    {
        bookingService.addJob(form);
    }
    @GetMapping("/status/{status}")
    public List<Bookings> list(@RequestBody @PathVariable Byte status)
    {
       
        return  bookingService.findByStatus(status);
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
      String filename = "bookings.csv";
      InputStreamResource file = new InputStreamResource(fileService.load());
  
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
          .contentType(MediaType.parseMediaType("application/csv"))
          .body(file);
    }
    //list flights
    @GetMapping
    public ResponseEntity<List<Bookings>> getAllBookings(
    @RequestParam(defaultValue = "0") Integer pageNo, 
    @RequestParam(defaultValue = "5") Integer pageSize,
    @RequestParam String flightNumber,
    @RequestParam(defaultValue = "bookingId") String sortBy,
    @RequestParam(defaultValue = "ASC")String sortDir) 
    {
     
    List<Bookings> list = bookingService.getAllBookings(pageNo, pageSize, sortBy,sortDir,flightNumber);
    return new ResponseEntity<List<Bookings>>(list, new HttpHeaders(), HttpStatus.OK); 
    }

    //Search Results
    // @GetMapping
    // public ResponseEntity<List<Bookings>> getByFlightNumber(
    // @RequestParam(defaultValue = "0") Integer pageNo, 
    // @RequestParam(defaultValue = "5") Integer pageSize,
    // @RequestParam String flightNumber,
    // @RequestParam(defaultValue = "bookingId") String sortBy,
    // @RequestParam(defaultValue = "ASC")String sortDir) 
    // {
    // List<Bookings> list = bookingService.getByFlightNumber(pageNo, pageSize, sortBy,sortDir,flightNumber);
    // return new ResponseEntity<List<Bookings>>(list, new HttpHeaders(), HttpStatus.OK); 
    // }
}
   

