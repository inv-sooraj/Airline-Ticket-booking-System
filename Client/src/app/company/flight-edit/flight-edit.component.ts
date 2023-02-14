import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';

@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styleUrls: ['./flight-edit.component.css']
})
export class FlightEditComponent {
  FlightEditForm!: FormGroup;
  seats: any = ["Economy", "Business", "First Class"];
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
    this.seatDetails().push(this.newData());
    this.today = new Date().toISOString().slice(0, 16);
    console.log("currentdate", this.today);
    this.getFlightById();
  }
  seatDetails(): FormArray {
    return this.FlightEditForm.get("seatDetails") as FormArray;
  }
  addCreds() {
    this.seatDetails().push(this.newData());
  }
  newData(): FormGroup {
    return this.formbuilder.group({
      seatType: ["", Validators.required],

      seatNo: ["", Validators.required],

      price: ["", Validators.required],
    });
  }
  //Method to add flight details
  updateFlight() {
    if (this.FlightEditForm.valid) {
      let param = {
        airplaneId: this.FlightEditForm.value.airplane,
        flightNumber: this.FlightEditForm.value.flightno,
        departure: this.FlightEditForm.value.departure,
        depDateTime: this.FlightEditForm.value.departureDT,
        destination: this.FlightEditForm.value.destination,
        destDateTime: this.FlightEditForm.value.ariivalDT,
        userId: this.userid,
        deleteFlag: 1,
        seats: this.FlightEditForm.value.seatDetails,
      };
      console.log("Flight form", param);
      this.apiservice.createFlight(param).subscribe({
        next: (result: any) => {
          alert("Created successfully");
          //  this.router.navigate(['/login'])
        },
        error: (err: any) => {
          alert(err.name);
          console.log(err);
        },
      });
    } else {
      this.status = true;
    }
  }
  cancel() {
    this.FlightEditForm.reset();
  }
  //Method to get aiplanes of currently logged in company()

  getPlaneName() {
    this.apiservice.getPlaneByCompany(this.userid).subscribe({
      next: (response: any) => {
        this.AirplaneDetails = response;
        console.log("airplane names",this.AirplaneDetails);
        
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch airplane details", "error");
      },
      complete: () => {},
    });
  }
  getFlightById(){
    this.apiservice.getPlaneDataById(this.flightId).subscribe({
      next: (response: any) => {
        this.response = response;
        // this.seatsArray=response.seats;
        for(let i of response.seats){
          this.seatsArray.push(i)
        }
        console.log("flight details",this.response);
        
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch flight details", "error");
      },
      complete: () => {},
    });
    console.log("array for seat",this.seatsArray);
    
  }
}
