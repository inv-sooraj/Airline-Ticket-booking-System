package com.airline.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.service.BookingService;
@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    public List<Bookings> list(){
    return bookingService.list();
    }
    @PostMapping("/addBooking")
    public void addJob(@RequestBody BookingForm form)
    {
        bookingService.addJob(form);
    }
}
