package com.airline.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.exception.ApplicationError;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.repository.BookingRepository;
import com.airline.reservation.security.util.SecurityUtil;
import com.airline.reservation.service.BookingService;
import com.airline.reservation.view.BookingListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Bookings> list() {
        return bookingRepository.findAll();
    }

    public void addBooking(BookingForm form) {
        System.out.println("status = " + form.getStatus());
        bookingRepository.save(new Bookings(form, form.getCancellation()));
    }

    @Override
    public List<Bookings> findByDeleteFlag(Byte flag) {
        return bookingRepository.findByDeleteFlag(flag);
    }

    public List<BookingListView> bookingList() {
        // TODO Auto-generated method stub
        return (List<BookingListView>) new BookingListView(bookingRepository.findAll());
    }

    //Booking Status Change - Booking id and Status are passed as parameters
    public ResponseEntity<ResBody> changeStatus(Integer bookingId, Byte status) {
        ResBody body = new ResBody();
        //Finds Booking by id
        Bookings booking = bookingRepository.findByBookingId(bookingId);
        System.out.println("Before Booking" + booking.getStatus());
        //Checks whether the status value is out of our desired range
        if (status > 3 || status < 0) {
            body.getErrors().add(new ApplicationError("1004", "Invalid Status Value"));
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else {
            booking.setStatus(status);
            bookingRepository.save(booking);
            return new ResponseEntity<>(body, HttpStatus.OK);
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

    @Override
    @Transactional
    public void deleteAllBYIds(List<Integer> integers) {
        System.out.println("ids  are :" + integers);
        bookingRepository.softDeleteAllIds(integers);
    }

    @Override
    public List<Bookings> getAllBookings(Integer pageNo, Integer pageSize, String sortBy, String sortDir, String flightNumber) {
        // TODO Auto-generated method stub
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Bookings> pagedResult = bookingRepository.findByFlightFlightNumber(paging, flightNumber);
        if (flightNumber == "") {
            Page<Bookings> AllpagedResult = bookingRepository.findAll(paging);
            if (AllpagedResult.hasContent()) {
                return AllpagedResult.getContent();
            } else {
                return new ArrayList<Bookings>();
            }
        }
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Bookings>();
        }
    }

    @Override
    public List<Bookings> findByUserUserId(Byte flag) {

        return bookingRepository.findByUserUserIdAndDeleteFlag((SecurityUtil.getCurrentUserId()), flag);
    }

    @Override
    public BookingListView getBooking(Integer bookingId, Byte flag) {

        return new BookingListView(bookingRepository.findByBookingIdAndDeleteFlag(bookingId, flag));

    }

    @Override
    public List<Bookings> findByStatus(Byte status,Byte deleteFlag,Integer flightId) {
        return bookingRepository.findByStatusAndDeleteFlagAndFlightFlightId(status,deleteFlag,flightId);
    }

    @Override
    public List<Bookings> findByCompany( Byte flag) {
        
                return bookingRepository.findByFlightUserUserIdAndDeleteFlag(SecurityUtil.getCurrentUserId(),flag);

    }

    @Override
    public List<Bookings> findByUserUserIdAndDeleteFlag(Integer userId, Byte flag) {
        // TODO Auto-generated method stub
        return bookingRepository.findByUserUserIdAndDeleteFlag(SecurityUtil.getCurrentUserId(), flag);
    }

    @Override
    public List<Bookings> findByUserUserIdAndDeleteFlag(Byte flag) {
        return bookingRepository.findByUserUserIdAndDeleteFlag(SecurityUtil.getCurrentUserId(), flag);
    }

    @Override
    public ResponseEntity<ResBody> cancelBooking(Integer bookingId, String reason,Byte status) {
        // TODO Auto-generated method stub
        ResBody body = new ResBody();
        //Finds Booking by id
        Bookings booking = bookingRepository.findByBookingId(bookingId);
        System.out.println("Before Booking" + booking.getStatus());
        //Checks whether the status value is out of our desired range
        if (status > 3 || status < 0) {
            body.getErrors().add(new ApplicationError("1004", "Invalid Status Value"));
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        } else {
            booking.setStatus(status);
            booking.setCancellation(reason);
            bookingRepository.save(booking);
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
    }

   
    @Override
    public void bookSeats(List<BookingForm> bookingForms) {
        List<Bookings> bookingsList = new ArrayList<>();

        for (BookingForm bookingForm : bookingForms) {
            Bookings booking = new Bookings(bookingForm, null);
            bookingsList.add(booking);
        }

        bookingRepository.saveAll(bookingsList);
    }
    
}
