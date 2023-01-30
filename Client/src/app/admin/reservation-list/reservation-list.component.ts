import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { BookingServiceService } from 'src/app/services/booking-service.service';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  
  constructor(private bookingService:BookingServiceService) { 
    
  }

  ngOnInit(): void {
  }
  export(){
    console.log("export function");
    // this.bookingService.download().subscribe();
    window.open("http://localhost:9091/bookings/download");
  }
}
