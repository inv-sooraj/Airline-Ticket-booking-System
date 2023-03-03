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

    List<Bookings> list();

    void addBooking(BookingForm form);

    List<Bookings> findByDeleteFlag(Byte flag);

    List<Bookings> getAllBookings(Integer pageNo, Integer pageSize, String sortBy, String sortDir, String flightNumber);

    ResponseEntity<ResBody> changeStatus(Integer bookingId, Byte status);

    void deleteAllBYIds(List<Integer> ids);

    List<Bookings> findByUserUserId(Byte flag);
    List<Bookings> findByStatus(Byte flag,Byte deleteFlag,Integer flightId);

    public BookingListView getBooking(Integer bookingId, Byte flag);

    public List<Bookings> findByCompany( Byte flag);

    List<Bookings> findByUserUserIdAndDeleteFlag(Integer userId, Byte flag);

    List<Bookings> findByUserUserIdAndDeleteFlag(Byte flag);

    ResponseEntity<ResBody> cancelBooking(Integer bookingId, String reason,Byte status);

    void bookSeats(List<BookingForm> bookingForms);

    List<Bookings> findByAllCancelled(Byte status, Byte deleteFlag);
}
