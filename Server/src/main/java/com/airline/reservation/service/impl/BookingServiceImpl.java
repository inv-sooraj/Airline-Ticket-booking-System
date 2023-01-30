package com.airline.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.exception.ApplicationError;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.repository.BookingRepository;
import com.airline.reservation.service.BookingService;
import com.airline.reservation.view.BookingListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    
    public List<Bookings> findByStatus(Byte status) {
        return bookingRepository.findByStatus(status);
    }

    // @Override
    // public List<BookingListView> bookingList() {
    //     // TODO Auto-generated method stub
    //     return (List<BookingListView>) new BookingListView(bookingRepository.findAll());
    // }
//list all Flights
    
    public List<Bookings> getAllBookings(Integer pageNo, Integer pageSize, String sortBy, String sortDir,String flightNumber) {
        // TODO Auto-generated method stub
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Bookings> pagedResult = bookingRepository.findByFlightFlightNumber(paging,flightNumber);
        if(flightNumber=="")
        {
           Page<Bookings> AllpagedResult = bookingRepository.findAll(paging);
           if(AllpagedResult.hasContent()) {
            return AllpagedResult.getContent();
            } 
            else {
                return new ArrayList<Bookings>();
            }
        }
        if(pagedResult.hasContent()) {
        return pagedResult.getContent();
        } 
        else {
            return new ArrayList<Bookings>();
        }
    }
    //Booking Status Change - Booking id and Status are passed as parameters
    public ResponseEntity<ResBody> changeStatus(Integer bookingId,Byte status ) {
        ResBody body = new ResBody();
        //Finds Booking by id
        Bookings booking=bookingRepository.findByBookingId(bookingId);
        System.out.println("Before Booking"+booking.getStatus());
        //Checks whether the status value is out of our desired range
        if(status>2 || status<0)
        {
            body.getErrors().add(new ApplicationError("1004","Invalid Status Value"));
            return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
        }
        else{
            booking.setStatus(status);
            bookingRepository.save(booking);
            return new ResponseEntity<>(body,HttpStatus.OK);
        }
    } 
    //list by search results
 // public List<Bookings> getByFlightNumber(Integer pageNo, Integer pageSize, String sortBy, String sortDir,String flightNumber) {
    //     // TODO Auto-generated method stub
    //     Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
    //     Pageable paging = PageRequest.of(pageNo, pageSize, sort);
    //     Page<Bookings> pagedResult = bookingRepository.findByFlightFlightNumber(paging,"rere");
    //     if(pagedResult.hasContent()) {
    //     return pagedResult.getContent();
    //     } 
    //     else {
    //         return new ArrayList<Bookings>();
    //     }  
    // }

 
    
}
