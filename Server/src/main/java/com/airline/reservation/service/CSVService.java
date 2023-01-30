package com.airline.reservation.service;
import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.csv.CSVHelper;
import com.airline.reservation.entity.Bookings;
import com.airline.reservation.repository.BookingRepository;
@Service
public class CSVService {

  @Autowired
  BookingRepository repository;
  
  public ByteArrayInputStream load() {
    
    List<Bookings> bookings = repository.findAll();
   
    
    ByteArrayInputStream in = CSVHelper.bookingsToCSV(bookings);
    return in;
  }
}