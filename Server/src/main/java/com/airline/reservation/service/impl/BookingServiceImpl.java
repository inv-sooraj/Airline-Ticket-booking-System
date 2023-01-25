package com.airline.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.repository.BookingRepository;
import com.airline.reservation.service.BookingService;
@Service
public class BookingServiceImpl implements BookingService{
   @Autowired
   private BookingRepository bookingRepository;
   public List<Bookings>list(){
        return bookingRepository.findAll();
     }
     
    public void addJob(BookingForm form) {
        System.out.println("status = "+form.getStatus());
        
         bookingRepository.save(new Bookings(form, form.getCancellation(), form.getStatus()));
    }
    
}
