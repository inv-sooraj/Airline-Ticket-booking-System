package com.airline.reservation.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.airline.reservation.entity.Bookings;
import com.airline.reservation.form.BookingForm;
import com.airline.reservation.json.ResBody;
import com.airline.reservation.service.BookingService;
import com.airline.reservation.service.CSVService;
import com.airline.reservation.view.BookingListView;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    CSVService fileService;

    @PostMapping("/addBooking")
    public void addBooking(@RequestBody BookingForm form) {
        bookingService.addBooking(form);
    }
    
    //list all booking details(for admin)
    
    @GetMapping("/status/{flag}")
    public List<Bookings> list(@RequestBody @PathVariable Byte flag) {

        return bookingService.findByDeleteFlag(flag);
    }
    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "bookings.csv";
        InputStreamResource file = new InputStreamResource(fileService.load());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
//downloadCancelled

@GetMapping("/download/{status}")
public ResponseEntity<Resource> getByStatus(@PathVariable byte status) {
    String filename = "cancelled_Bookings.csv";
    InputStreamResource file = new InputStreamResource(fileService.loadCancelledBookings(status));
    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
            .contentType(MediaType.parseMediaType("application/csv"))
            .body(file);
}
    //delete Bookings 
    @DeleteMapping
    public void delete(@RequestParam("ids") ArrayList<Integer> ids) {
        System.out.println("deleting");
        bookingService.deleteAllBYIds(ids);
    }

    //list bookings
    @GetMapping
    public ResponseEntity<List<Bookings>> getAllBookings(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam String flightNumber,
            @RequestParam(defaultValue = "bookingId") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {
        List<Bookings> list = bookingService.getAllBookings(pageNo, pageSize, sortBy, sortDir, flightNumber);
        return new ResponseEntity<List<Bookings>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //change booking status(approve,reject)
    @PutMapping("/changeStatus/{bookingId}/{status}")
    public ResponseEntity<ResBody> changeStatus(@PathVariable Integer bookingId, @PathVariable Byte status) {
        System.out.println("status=" + status);
        System.out.println("booking id=" + bookingId);
        return bookingService.changeStatus(bookingId, status);

    }
    //cancellation from passenger side
    @PutMapping("/cancelBooking/{bookingId}/{reason}/{status}")
    public ResponseEntity<ResBody> cancelBooking(@PathVariable Integer bookingId, @PathVariable String reason,@PathVariable Byte status) {
        System.out.println("reason=" + reason);
        System.out.println("booking id=" + bookingId);
        return bookingService.cancelBooking(bookingId, reason,status);

    }
    //get bookings done by individual user(for passenger)
    
    @GetMapping("/getById/{flag}")
    public List<Bookings> userBookings(@PathVariable("flag") Byte flag) {
        return bookingService.findByUserUserIdAndDeleteFlag(flag);
    }
    
    //To get cancelled booking details
    
    @GetMapping("/getCancelled/{status}")
    public List<Bookings> getCancelled(@PathVariable("status") Byte status) {
        return bookingService.findByStatus(status);
    }
    
    //Get booking details of an id
    
    @GetMapping("/{bookingId}/{flag}")
    public BookingListView get(@PathVariable("bookingId") Integer bookingId, @PathVariable("flag") Byte flag) {
        return bookingService.getBooking(bookingId, flag);
    }
    
    /*To get booking deatils of a particular company(for company)*/
    
    @GetMapping("/getByCompany/{flag}")
    public List<Bookings> userBookingsByCompany(@PathVariable("flag") Byte flag) {
        return bookingService.findByCompany(flag);
    }
}
