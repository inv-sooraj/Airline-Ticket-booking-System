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
  selectedSeatPrice!: number;
  depDateTime: any;
  flightId: any;
  randomFlights: any;
  seatBookingFormArray:any;
  destDateTime: any;
  depTime: any;
  seatPrice:any;
  seatList:any
  

  myForm!: FormGroup;
  destTime: any;
  

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private datePipe: DatePipe,
    private route: ActivatedRoute,
    private apiService: ApiService
  ) {}

 

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
      seats: this.fb.array([
        this.fb.group({
          seatId: ['', Validators.required],
          price: ['', Validators.required],
          number: ['', Validators.required]
        })
      ])
    });
  
    console.log('SEAT DATA : ');
  
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
    this.seats.push(this.fb.group({
      seatId: ['', Validators.required],
      price: ['', Validators.required],
      number: ['', Validators.required]
    }));
  }
  
  removeSeat(index: number) {
    const seats = this.seats;
    seats.removeAt(index);
  }
  onChange(formArrayId:any,seatId:any){
    console.log("Form array id = "+formArrayId+" and Seat Id ="+seatId)
    // const seatGroup = (this.myForm.get('seats') as FormArray).at(formArrayId) as FormGroup;
    // seatGroup.get('price')?.setValue(10)
    // console.log(seatGroup.value)

    // const seats = this.myForm.get('seats') as FormArray;
    //   const seat = seats.at(seatId);
    //   seat.get('price')?.setValue(10);
      // seat.get('price').setValue(price);
      if(seatId==2){
        this.selectedSeatPrice=42;
      }else{
        this.selectedSeatPrice=800;
      }
// Assuming your FormArray is called "rowsFormArray"
const rowFormGroup = this.seats.at(formArrayId) as FormGroup;
rowFormGroup.patchValue({
  price: this.selectedSeatPrice
});

      const seatControl=this.seats.controls[formArrayId];
      seatControl.get('price')?.setValue(this.selectedSeatPrice)
  }
// change(i:any,f:any){
//   alert(f)
//   let currentarray= this.seatBookingFormArray.at(f)as FormGroup;
//   currentarray.patchValue({price:this.seatList[i].price})
//   console.log(currentarray.value);
  
//   this.myForm.get('seats')?.patchValue({price:this.seatList[i].price})
//   console.log(this.myForm.value);
//   this.myForm.controls['price'].patchValue(this.seatList[i].price)
// }
  onSubmit() {
    const seats = this.seats  ;
    for (let i = 0; i < seats.length; i++) {
      const seat = seats.at(i);
      seat.get('price')?.setValue(this.seatPrice);
    }
    console.log(this.myForm.value);
  }
}
