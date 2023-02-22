import { Component, ComponentFactoryResolver, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/api.service';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { of, VirtualTimeScheduler } from 'rxjs';
interface Seat {
  seatId: string;
  seatType: string;
  price: number;
  number: number;
}
@Component({
  selector: 'app-flight-detail',
  templateUrl: './flight-detail.component.html',
  styleUrls: ['./flight-detail.component.css']
})
export class FlightDetailComponent implements OnInit {
  items: any;
  depDateTime: any;
  flightId: any;
  randomFlights: any;
  seatBookingFormArray:any;
  destDateTime: any;
  depTime: any;
  seatList: Seat[] = [];
  seatData: any;
  seatPrice!: string;
  sPrice:any
  myForm!: FormGroup;
  destTime: any;
  seatTypes: any;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {}

  createSeatFormGroup(): FormGroup {
    return this.fb.group({
      seatId: [''],
      price: [''],
      number: ['']
    });
  }

  getRandom() {
    this.apiService.getTwoRandom().subscribe({
      next: (response: any) => {
        console.log('RANDOM FLIGHTS ARE : ');
        this.randomFlights = response;
        console.log(this.randomFlights);
      }
    });
  }

  ngOnInit() {
    this.getFlightDetails();

    //Get Random Flights
    this.getRandom();

    this.myForm = this.fb.group({
      seats: this.fb.array([])
    });
    this.addSeat();
    console.log('SEAT DATA : ');
    console.log(this.seatData);
  }

  // Form Array Adding, Removing, Submitting Functions
  get seats():FormArray {
    this.seatBookingFormArray=this.myForm.get('seats') as FormArray;
    return this.myForm.get('seats') as FormArray;
  }

  getFlightDetails() {
    // Get Flight Data According to Flight Id - Start
    this.route.params.subscribe(params => {
      const flightId = params['flightId'];
      this.flightId = flightId;

      this.apiService.getFlightDetail(flightId).subscribe({
        next: (response: any) => {
          this.items = response;
          this.seatList = this.items.seats;
          console.log(' SEAT DATA :');
          console.log(this.items.seats);
          this.depDateTime = this.items.depDateTime.slice(0, 10);
          this.destDateTime = this.items.destDateTime.slice(0, 10);
          let formattedDepTime = this.datePipe.transform(
            this.items.depDateTime,
            'h:mm a'
          );
          this.depTime = formattedDepTime;
          let formattedDestTime = this.datePipe.transform(
            this.items.depDateTime,
            'h:mm a'
          );
          this.destTime = formattedDestTime;
          console.log('Flight Detail = ' + this.items.flightId);
      
    },
    error: (err: any) => {
      alert("Failed");
    },
    complete: () => {},
  });
});
  }
  addSeat() {
    const seats = this.seats
    // this.seatList.forEach(seat => {
    const seatGroup = this.fb.group({
      seatId: new FormControl(''),
      price: new FormControl(''),
      number: new FormControl('')
    });
  
    seats.push(seatGroup);
    this.setSeatOptions(seats.length - 1);
  // });
  }
  
  removeSeat(index: number) {
    const seats = this.seats;
    seats.removeAt(index);
  }
  
change(i:any,f:any){
  alert(f)
  let currentarray= this.seatBookingFormArray.at(f)as FormGroup;
  currentarray.patchValue({price:this.seatList[i].price})
  console.log(currentarray.value);
  
  this.myForm.get('seats')?.patchValue({price:this.seatList[i].price})
  console.log(this.myForm.value);
  this.myForm.controls['price'].patchValue(this.seatList[i].price)
}
  onSubmit() {
    const seats = this.seats  ;
    for (let i = 0; i < seats.length; i++) {
      const seat = seats.at(i);
      seat.get('price')?.setValue(this.seatPrice);
    }
    console.log(this.myForm);
  }
  
  setSeatOptions(index: number) {
    // Fetch seat data from backend or any other data source
    const seats = [
      { id: 1, name: 'First Class', price: 100 },
      { id: 2, name: 'Business Class', price: 50 },
      { id: 3, name: 'Economy Class', price: 25 }
    ];
  }
}