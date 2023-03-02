import { AstMemoryEfficientTransformer } from "@angular/compiler";
import { Component, OnInit } from "@angular/core";
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormArray,
  FormControl,
} from "@angular/forms";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { AlertService } from "src/app/alert.service";
import { ApiService } from "src/app/api.service";
import { DatePipe } from '@angular/common';
@Component({
  selector: "app-flight-edit",
  templateUrl: "./flight-edit.component.html",
  styleUrls: ["./flight-edit.component.css"],
})
export class FlightEditComponent implements OnInit {
  FlightEditForm!: FormGroup;
  status: any = false;
  AirplaneDetails: any;
  userid: any;
  today: any;
  flightId: any;
  seatId: any;
  response: any;
  seatsArray: any[] = [];
  seatDetails: any[] = [];
  seatResponse: any;
  displayStyle = "none";
  minDate:any;
  ArrivalDate:any;
  deptDate:any;
  destDate:any;
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    private alertservice: AlertService,
    private route: ActivatedRoute,
    private datePipe: DatePipe
  ) {
    this.FlightEditForm = this.formbuilder.group({
      airplane: ["", Validators.required],
      flightno: [
        "",
        [Validators.required, Validators.pattern("^[a-zA-Z0-9]*$")],
      ],
      departure: [
        "",
        [Validators.required, Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")],
      ],
      departureDT: ["", Validators.required],
      destination: [
        "",
        [Validators.required, Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")],
      ],
      ariivalDT: ["", Validators.required],
      seatid: [{disabled: true}, Validators.required],
      seatType: ["", Validators.required],
      number: ["", Validators.required],
      price: ["", Validators.required],
    });
  }

  ngOnInit(): void {
    this.route.params.forEach((params: Params) => {
      this.flightId = params["flightId"];
    });
    this.userid = localStorage.getItem("userid");
    this.getPlaneName();
    this.today = new Date().toISOString().slice(0, 16);
    this.getFlightById();
    
  }
  newData() {
    let data = {
      seatId: this.FlightEditForm.value.seatid,
      seatType: this.FlightEditForm.value.seatType,

      number: this.FlightEditForm.value.number,

      price: this.FlightEditForm.value.price,
    };
    return data;
  }

  //Method to add flight details
  updateFlight() {
    this.destDate = this.datePipe.transform(this.FlightEditForm.value.ariivalDT,'yyyy-MM-ddTHH:mm');
    this.deptDate = this.datePipe.transform( this.FlightEditForm.value.departureDT,'yyyy-MM-ddTHH:mm');
    let param = {
      flightNumber: this.FlightEditForm.value.flightno,
      departure: this.FlightEditForm.value.departure,
      depDateTime:this.deptDate,
      destination: this.FlightEditForm.value.destination,
      destDateTime: this.destDate,
      airplaneId: JSON.stringify(this.FlightEditForm.value.airplane),
      userId: this.userid,
      seats: this.seatDetails,
    };
    console.log("This is parameters",param);
    
    this.apiservice.sendUpdateflight(param, this.flightId).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Successfully edited", "Success");
        this.router.navigate(["/flight-list"]);
      },
      error: (err: any) => {

        console.log("this is the error",err);
        
        this.alertservice.showError("Failed", "error");
      },
      complete: () => {},
    });
  }

  cancel() {
    this.router.navigate(["/flight-list"]);
  }
  //Method to get aiplanes of currently logged in company()

  getPlaneName() {
    this.apiservice.getPlaneByCompany(this.userid).subscribe({
      next: (response: any) => {
        this.AirplaneDetails = response;
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch airplane details", "error");
      },
      complete: () => {},
    });
  }
  getFlightById() {
    this.apiservice.getPlaneDataById(this.flightId).subscribe({
      next: (responseData: any) => {
        this.response = responseData;
        for (let i of responseData.seats) {
          this.seatsArray.push(i);
        }
        this.FlightEditForm.controls["airplane"].setValue(
          responseData.airplane.airplaneId
        );
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch flight details", "error");
      },
      complete: () => {},
    });
  }
  getSeatById() {
    this.apiservice.getSeatById(this.seatId).subscribe({
      next: (responseData: any) => {
        this.seatResponse = responseData;
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch flight details", "error");
      },
      complete: () => {},
    });
  }
  openPopup(id: any) {
    this.displayStyle = "block";
    this.seatId = id;
    this.getSeatById();
  }
  closePopup() {

    this.displayStyle = "none";
  }
  updateSeat() {
    let param = {
      seatId: this.FlightEditForm.value.seatid,
      seatType: this.FlightEditForm.value.seatType,
      number: this.FlightEditForm.value.number,
      price: this.FlightEditForm.value.price,
    };
    this.seatDetails.push(param);
    this.closePopup();
  }
  setDate(event:any){
    this.minDate=event.target.value;
    
  }
  arrivalDate(){
    if(this.minDate == null){
      this.ArrivalDate=this.response.depDateTime;
    }
    else{
      this.ArrivalDate=this.minDate
    }
  }
}