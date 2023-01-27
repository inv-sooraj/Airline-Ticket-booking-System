package com.airline.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.repository.BookingRepository;
import com.airline.reservation.service.BookingService;
import com.airline.reservation.view.BookingListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
