import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-booking-details',
  templateUrl: './booking-details.component.html',
  styleUrls: ['./booking-details.component.css']
})
export class BookingDetailsComponent implements OnInit {

  ngOnInit(): void {
    this.getBookingDetails();
  }
  getBookingDetails(){
    
  }

}
