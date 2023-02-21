import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  today:any;
  searchForm!: FormGroup;
items:any;
baseUrl = environment.baseUrl;
   
  constructor(private http:HttpClient,private apiservce: ApiService,private alertservice: AlertService,private formBuilder:FormBuilder) { 
    this.searchForm = new FormGroup({
      destination: new FormControl(),
      departure: new FormControl(),
      depDateTime:new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.getRandomFlight();
    this.today = new Date().toISOString().split('T')[0];
  }
  isBeforeToday() {
    const selectedDate = new Date(this.searchForm.value.myDate);
    const today = new Date();
    return selectedDate < today;
  }
getRandomFlight(){
  this.apiservce.getRandom().subscribe({
    next: (response: any) => {
      this.items = response;
      console.log("Random Flights are : ", this.items);
    },
    error: (err: any) => {
      this.alertservice.showError("Failed to load random Flights", "Error");
    },
    complete: () => {},
  });
}
// onSubmit(){
//   const formValue = this.searchForm.value;
//   // console.log(formValue);
//   let params = new HttpParams();
//   params = params.append('destination', formValue.destination);
//   params = params.append('departure', formValue.departure);
//   params = params.append('depDateTime',formValue.depDateTime);
//   this.http.get(this.baseUrl+'/flight', { params }).subscribe(
//     response => console.log('Search results:', response),
//     error => console.error('Error searching:', error)
//   );
// }
}
