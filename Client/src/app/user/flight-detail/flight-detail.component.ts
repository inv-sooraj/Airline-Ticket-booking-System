import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/api.service';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { VirtualTimeScheduler } from 'rxjs';

@Component({
  selector: 'app-flight-detail',
  templateUrl: './flight-detail.component.html',
  styleUrls: ['./flight-detail.component.css']
})
export class FlightDetailComponent implements OnInit {
  items:any;
  depDateTime:any
  flightId:any
  destDateTime:any
  selectedSeatTypes = []; 
  depTime:any
  seatData:any

  destTime:any
  seatTypes:any;
  myForm!: FormGroup;
  constructor(private fb: FormBuilder, private http: HttpClient,private datePipe: DatePipe,private route: ActivatedRoute,private apiService:ApiService) { 
   
  }
  

  ngOnInit() {
    
    
    this.route.params.subscribe(params => {
      const flightId = params['flightId'];
      this.flightId=flightId
      this.apiService
      .getFlightDetail(flightId)
      .subscribe({
        next: (response: any) => {
          this.items = response;
          this.seatData=this.items.seats;
          console.log(" SEAT DATA :")
          console.log(this.items.seats);
          this.seatTypes=
          this.depDateTime = this.items.depDateTime.slice(0, 10);
          this.destDateTime = this.items.destDateTime.slice(0, 10);
          let formattedDepTime = this.datePipe.transform(this.items.depDateTime, 'h:mm a');
          this.depTime=formattedDepTime;
          let formattedDestTime = this.datePipe.transform(this.items.depDateTime, 'h:mm a');
          this.destTime=formattedDestTime;
          console.log("Flight Detail = "+this.items.flightId)
        },
        error: (err: any) => {
          alert("Failed");
        },
        complete: () => {},
      });
  });
  this.myForm = this.fb.group({
    seats: this.fb.array([
      this.fb.group({
        seatId: [],
        price: [{value: '', disabled: true}],
        number: []
      })
    ])
  }); 
  
 // Assuming you have a form array called 'seatsFormArray'
// Assuming you have an array of seat data called 'seatDetails'
// And a form array called 'seatData'

console.log("SEAT DATA ")
console.log(this.seatData)
// this.addSeat();
this.getIdAndType();
// Subscribe to seatId changes

  }
  get seats() {
    return this.myForm.get('seats') as FormArray;
  }

  addSeat() {
    const seat = this.fb.group({
      class: [],
      price: [{value: '', disabled: true}],
      count: []
    });
    this.seats.push(seat);
  }
 
  
  removeSeat(index: number) {
    this.seats.removeAt(index);
  }
  onSubmit() {
    const formValues = this.myForm.value;
    console.log(formValues);
  }
  getIdAndType(){
    this.apiService.getSeatIdAndTypeId(this.flightId).subscribe({
      next: (response: any) => {

        this.seatTypes = response;
       
        console.log("Seat Id and Type ", this.seatTypes);
      }
    });
  }
  updatePrice(seatId: string, index: number) {
    if (this.myForm) {
      const seat = this.myForm.get('seats')
      console.log("SINGLE SEAT ");
      console.log(seat) 
    }
  }
}
