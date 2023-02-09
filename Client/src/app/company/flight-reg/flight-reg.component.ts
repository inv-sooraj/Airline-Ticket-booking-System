import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';
// import { ApiService } from '../api.service';
import { DatePipe } from '@angular/common';
import { LoginComponent } from 'src/app/shared/login/login.component';
@Component({
  selector: 'app-flight-reg',
  templateUrl: './flight-reg.component.html',
  styleUrls: ['./flight-reg.component.css']
})
export class FlightRegComponent implements OnInit {
 
  FlightRegForm!:FormGroup;
  seats: any = ['Economy', 'Business','First Class'];
  status:any=false;
  AirplaneDetails:any;
  userid:any;
    now: Date = new Date();
     yesterdayDate:Date = new Date();
    minDate: any;
    maxDate: any;
  setDob: any ;
  constructor(private formbuilder:FormBuilder,private router:Router,private apiservice:ApiService,private alertservice:AlertService) {
    this.FlightRegForm=this.formbuilder.group({
      airplane:['',Validators.required],
      flightno:['',[Validators.required,Validators.pattern("^[a-zA-Z0-9]*$")]],
      departure:['',[Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      departureDT:['',Validators.required],
      destination:['',[Validators.required,Validators.pattern("^[a-zA-Z][a-zA-Z ]+$")]],
      ariivalDT:['',Validators.required],
      seatDetails:this.formbuilder.array([])
        });
        let currentDateTime =new Date(); 
   }


  ngOnInit(): void {

    this.userid=localStorage.getItem('userid');
    this.getPlaneName() ;
    this.seatDetails().push(this.newData());
    this.getToday();
     
    
  }
  getToday(){
    
    
    //If you want to disable past dates from current date, you can set mindate to current date.
    
    this.minDate = { year: this.now.getFullYear(), month: this.now.getMonth(), day: this.now.getDate() };
    var datePipe = new DatePipe('en-IND');
    this.setDob = datePipe.transform(this.minDate, 'yyyy-mm-dd hh:mm');
    this.yesterdayDate.setDate(this.setDob - 1); 
    console.log("currentdate",this.yesterdayDate);
    return this.yesterdayDate;
    
   
  }
  // changePlane(e:any) {
  //   this.FlightRegForm?.get('airplane')?.setValue(e.target.value, {
  //     onlySelf: true
  //   });
  // }
//   changeSeat(e:any) {
//     console.log(e.value)
//     this.FlightRegForm?.get('seatType')?.setValue(e.target.value, {
//       onlySelf: true
//     });
// }
seatDetails() : FormArray {

  return this.FlightRegForm.get("seatDetails") as FormArray

}
addCreds() {
  this.seatDetails().push(this.newData());
}
newData(): FormGroup {

  return this.formbuilder.group({

    seatType: ['',Validators.required],

    seatNo: ['',Validators.required],

    price: ['',Validators.required]
  });
}
addFlight()
  {
    if(this.FlightRegForm.valid){
      let param = {
        "airplaneId": this.FlightRegForm.value.airplane,
        "flightNumber": this.FlightRegForm.value.flightno,
        "departure":  this.FlightRegForm.value.departure,
        "depDateTime":  this.FlightRegForm.value.departureDT,
        "destination":  this.FlightRegForm.value.destination,
        "destDateTime":  this.FlightRegForm.value.ariivalDT,
        "seatDetails":this.FlightRegForm.value.seatDetails
      }
    console.log("Flight form",param);
      this.apiservice.createFlight(param).subscribe({
        next: (result: any) => {
        alert('Created successfully')
      //  this.router.navigate(['/login'])   
    },
    error: (err: any) => {
      alert(err.name);
      console.log(err);
    }  
    });
    }
    else{
        this.status=true;
    }
  }
  cancel()
  {
    this.FlightRegForm.reset();
  }
  getPlaneName() {
    this.apiservice.getPlaneByCompany(this.userid).subscribe({
      next: (response: any) => {
        this.AirplaneDetails = response;
      },
      error: (err: any) => {
        this.alertservice.showError("Couldnt fetch airplane details", "error")
      },
      complete: () => { }
    });
  }
}

