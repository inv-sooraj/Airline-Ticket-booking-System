<<<<<<< HEAD
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

@Component({
  selector: "app-flight-edit",
  templateUrl: "./flight-edit.component.html",
  styleUrls: ["./flight-edit.component.css"],
})
export class FlightEditComponent implements OnInit {
  FlightEditForm!: FormGroup;
  // seats: any = ["Economy", "Business", "First Class"];
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
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    private alertservice: AlertService,
    private route: ActivatedRoute
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
      seatid: ["", Validators.required],
      seatType: ["", Validators.required],
      number: ["", Validators.required],
      price: ["", Validators.required],
      // seatDetails: this.formbuilder.array([]),
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
    // this.seatDetails().push(this.newData());
  }
  // seatDetails(): FormArray {
  //   return this.FlightEditForm.get("seatDetails") as FormArray;
  // }
  // addCreds() {
  //   this.seatDetails.push(this.newData());
  // }
  // delete(index:any){
  //   this.seatDetails.removeAt(index);
  // }
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
    // this.addCreds();
    // console.log("new data",this.seatDetails());
    let param = {
      flightNumber: this.FlightEditForm.value.flightno,
      departure: this.FlightEditForm.value.departure,
      depDateTime: this.FlightEditForm.value.departureDT,
      destination: this.FlightEditForm.value.destination,
      destDateTime: this.FlightEditForm.value.ariivalDT,
      airplaneId: JSON.stringify(this.FlightEditForm.value.airplane),
      userId: this.userid,
      seats: this.seatDetails,
      // deleteFlag: 1,
      // seats: this.FlightEditForm.value.seatDetails,
    };
    console.log("parameters", param);
    this.apiservice.sendUpdateflight(param, this.flightId).subscribe({
      next: (response: any) => {
        this.alertservice.showSuccess("Successfully edited", "Success");
        this.router.navigate(["/flight-list"]);
      },
      error: (err: any) => {
        console.log(err);

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
        // this.FlightEditForm.setValue({
        //   airplane: this.response.airplane.airplaneId,
        // });
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch airplane details", "error");
        console.log(err);
      },
      complete: () => {},
    });
  }
  getFlightById() {
    this.apiservice.getPlaneDataById(this.flightId).subscribe({
      next: (responseData: any) => {
        this.response = responseData;
        // this.seatsArray=response.seats;
        console.log("response", responseData);

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
    console.log("seat", this.seatsArray);
  }
  getSeatById() {
    this.apiservice.getSeatById(this.seatId).subscribe({
      next: (responseData: any) => {
        this.seatResponse = responseData;
        console.log("Data from seat table", this.seatResponse);
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
    console.log("seat id", this.seatId);
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
    console.log("params of seats", this.FlightEditForm.value.seatid);

    this.seatDetails.push(param);
    console.log("array after editing one row", this.seatDetails);
    this.closePopup();
  }
}
=======
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styleUrls: ['./flight-edit.component.css']
})
export class FlightEditComponent implements OnInit{
  FlightEditForm!: FormGroup;
  // seats: any = ["Economy", "Business", "First Class"];
  status: any = false;
  AirplaneDetails: any;
  userid: any;
  today: any;
  flightId: any;
  response:any;
  seatsArray:any[]=[];
  constructor(
    private formbuilder: FormBuilder,
    private router: Router,
    private apiservice: ApiService,
    private alertservice: AlertService,
    private route: ActivatedRoute
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
      seatDetails: this.formbuilder.array([]),
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
    this.seatDetails().push(this.newData());
  }
  seatDetails(): FormArray {
    return this.FlightEditForm.get("seatDetails") as FormArray;
  }
  addCreds() {
    this.seatDetails().push(this.newData());
  }
  delete(index:any){
    this.seatDetails().removeAt(index);
  }
  newData(): FormGroup {
    return this.formbuilder.group({
      seatType: this.FlightEditForm.get('seatType'),

      number: this.FlightEditForm.get('number'),

      price: this.FlightEditForm.get('price'),
    });
  }
  
  //Method to add flight details
  updateFlight() {
    // this.addCreds();
  // console.log("new data",this.seatDetails());
    let param = {
      flightNumber: this.FlightEditForm.value.flightno,
      departure: this.FlightEditForm.value.departure,
      depDateTime: this.FlightEditForm.value.departureDT,
      destination: this.FlightEditForm.value.destination,
      destDateTime: this.FlightEditForm.value.ariivalDT,
      airplaneId: this.FlightEditForm.value.airplane,
      userId: this.userid,
      // deleteFlag: 1,
      seats: this.FlightEditForm.value.seatDetails,
    };
    console.log("parameters",this.FlightEditForm.value.seatDetails);
    this.apiservice.sendUpdateflight(param, this.flightId).subscribe({
      next: (response: any) => {
        
       this.alertservice.showSuccess("Successfully edited","Success")
        
      },
      error: (err: any) => {

        this.alertservice.showError("Failed", "error");
      },
      complete: () => {},
    });
  }
  
  cancel() {
    this.FlightEditForm.reset();
  }
  //Method to get aiplanes of currently logged in company()

  getPlaneName() {
    this.apiservice.getPlaneByCompany(this.userid).subscribe({
      next: (response: any) => {
        this.AirplaneDetails = response;
        // this.FlightEditForm.setValue({
        //   airplane: this.response.airplane.airplaneId, 
        // });
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch airplane details", "error");
      },
      complete: () => {},
    });
  }
  getFlightById(){
    this.apiservice.getPlaneDataById(this.flightId).subscribe({
      next: (responseData: any) => {
        this.response = responseData;
        // this.seatsArray=response.seats;
        for(let i of responseData.seats.seatId){
          this.seatsArray.push(i)
        }
        this.FlightEditForm.controls['airplane'].setValue(responseData.airplane.airplaneId);
        
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch flight details", "error");
      },
      complete: () => {},
    });
    console.log("seat",this.seatsArray);
    
  }
 
}
>>>>>>> 4811df5aacee65ea25f7b2324b95b1b61b76d091
