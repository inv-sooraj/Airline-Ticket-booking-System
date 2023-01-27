package com.airline.reservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.view.BookingListView;
@Service
public interface BookingService {
   List<Bookings>list();
   void addJob(BookingForm form);
   List<Bookings> findByStatus(Byte status);
   List<BookingListView> bookingList();
   
}
