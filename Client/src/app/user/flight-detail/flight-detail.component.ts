import { Component, ComponentFactoryResolver, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { ApiService } from "src/app/api.service";
import { DatePipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormControl,
} from "@angular/forms";
import { of, VirtualTimeScheduler } from "rxjs";
import { ThisReceiver } from "@angular/compiler";
interface Seat {
  seatId: string;
  seatType: string;
  price: number;
  number: any;
}
@Component({
  selector: "app-flight-detail",
  templateUrl: "./flight-detail.component.html",
  styleUrls: ["./flight-detail.component.css"],
})
export class FlightDetailComponent{
  seatIds!: any[];
  items: any;
  selectedSeatPrice!: number;
  depDateTime: any;
  flightId: any;
  randomFlights: any;
  seatBookingFormArray: any;
  destDateTime: any;
  depTime: any;
  seatPrice: any;
  seatList!: any[];
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
        console.log("RANDOM FLIGHTS ARE : ");
        this.randomFlights = response;
        console.log(this.randomFlights);
      },
    });
  }

  ngOnInit() {
    // Get Flight Data According to Flight Id - Start
    this.route.params.subscribe((params) => {
      const flightId = params["flightId"];
      this.flightId = flightId;
      
   // Call the API to get seatIds
  this.apiService.getSeatId(this.flightId).toPromise().then((response: any) => {
    this.seatIds = response;
    console.log("SEAT IDs ARE :", this.seatIds);

    // Once seatIds is populated, create the form group
    this.myForm = this.fb.group({
      seats: this.fb.array([this.createSeatFormGroup()]),
    });
  });
      this.apiService.getFlightDetail(flightId).subscribe({
        next: (response: any) => {
          this.items = response;
          this.seatList = this.items.seats;
          console.log(" SEAT DATA =", this.seatList);
          this.depDateTime = this.items.depDateTime.slice(0, 10);
          this.destDateTime = this.items.destDateTime.slice(0, 10);
          let formattedDepTime = this.datePipe.transform(
            this.items.depDateTime,
            "h:mm a"
          );
          this.depTime = formattedDepTime;
          let formattedDestTime = this.datePipe.transform(
            this.items.depDateTime,
            "h:mm a"
          );
          this.destTime = formattedDestTime;
          console.log("Flight Detail = " + this.items.flightId);
        },
        error: (err: any) => {
          alert("Failed");
        },
        complete: () => {},
      });
     
    });
    this.getRandom(); 
  }
  createSeatFormGroup(): FormGroup {
    return this.fb.group({
      userId:localStorage.getItem("userid"),
      seatId: ["", Validators.required],
      price: ["", Validators.required],
      qty: ['',Validators.required],
      status:[1],
      flightId:[this.flightId]
    });
  }
  addSeat() {
    const newSeat = this.createSeatFormGroup();
    this.getSeats().push(newSeat);
  }
  // Form Array Adding, Removing, Submitting Functions
  get seats(): FormArray {
    this.seatBookingFormArray = this.myForm.get("seats") as FormArray;
    return this.myForm.get("seats") as FormArray;
  }
  getSeats(): FormArray {
    return this.myForm.get("seats") as FormArray;
  }
  removeSeat(index: number) {
    const seats = this.seats;
    seats.removeAt(index);
  }
  onChange(formArrayId: any, seatId: any) {
    alert(seatId);
    console.log("Form array id = " + formArrayId + " and Seat Id =" + seatId);
    this.apiService.getSeatPrice(seatId, formArrayId).subscribe({
      next: (response: any) => {
        this.selectedSeatPrice = response;
        const rowFormGroup = this.seats.at(formArrayId) as FormGroup;
        rowFormGroup.patchValue({
          price: this.selectedSeatPrice,
        });
        const seatControl = this.seats.controls[formArrayId];

        seatControl.get("price")?.setValue(this.selectedSeatPrice);
        seatControl.get("seatId")?.setValue(seatId);
      },
      error: (err: any) => {
        alert("failed");
      },
      complete: () => {},
    });
  }
  onSubmit() { 
    

     console.log("on Submit");
console.log(this.seats.value);
    const seats = this.seats;
    for (let i = 0; i < seats.length; i++) {
      const seat = seats.at(i);
      seat.get("price")?.setValue(this.seatPrice);
    }
    // Get the form data for all seats
    
   // Assuming your form array is named 'seats'
  //  const param=this.seats.value
  //  console.log(param)
   this.apiService.addBooking(this.seats.value).subscribe({
   next:(response:any)=>{
    console.log("Succesfully Send Data")
   }
   })
  this.seats.reset();
}
}
