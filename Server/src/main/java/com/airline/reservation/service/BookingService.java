package com.airline.reservation.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.view.BookingListView;
@Service
public interface BookingService {
   List<Bookings>list();
   void addBooking(BookingForm form);
   List<Bookings> findByDeleteFlag(Byte flag);
   // List<BookingListView> bookingList();
   List<Bookings> getAllBookings(Integer pageNo, Integer pageSize, String sortBy,String sortDir,String flightNumber);
   // List<BookingListView> bookingList();  
   // List<Bookings> getByFlightNumber(Integer pageNo, Integer pageSize, String sortBy, String sortDir,
   //       String flightNumber);
ResponseEntity<ResBody> changeStatus(Integer bookingId,Byte status);
void deleteAllBYIds(List<Integer> ids);
}
