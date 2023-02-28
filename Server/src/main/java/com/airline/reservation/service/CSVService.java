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
        List<Bookings> tutorials = repository.findAll();
        ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
        return in;
    }
    public ByteArrayInputStream loadCancelledBookings(byte status,byte deleteFlag) {
        List<Bookings> cancelledBookings = repository.findByStatusAndDeleteFlag(status,deleteFlag);
        ByteArrayInputStream in = CSVHelper.cancelledBookingCSV(cancelledBookings);
        return in;
    }
}
