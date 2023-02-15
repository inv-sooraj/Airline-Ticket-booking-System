import { HttpClient, HttpParams } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AlertService } from 'src/app/alert.service';
import { ApiService } from 'src/app/api.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-user-flight-list',
  templateUrl: './user-flight-list.component.html',
  styleUrls: ['./user-flight-list.component.css']
})
export class UserFlightListComponent {
  baseUrl = environment.baseUrl;
  destination: any;
  departure: any
  depDateTime: any
  searchResults:any
  constructor(private route:ActivatedRoute,private http:HttpClient,private alertService:AlertService,private apiService:ApiService){}
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.destination = params.get('destination');
      this.departure = params.get('departure');
      this.depDateTime=params.get('depDateTime');
    });
  //     let param = new HttpParams();
  // param = param.append('destination', this.destination);
  // param = param.append('departure',  this.departure);
  // param = param.append('date', this.date);
  //  this.http.get(this.baseUrl+'/flight', { param }).subscribe(
  //   response => console.log('Search results:', response),
  //   error => console.error('Error searching:', error)
  // );
  let params = new HttpParams().set('destination', this.destination);
  params = params.append('departure', this.departure)
  .append('depDateTime',this.depDateTime);
  console.log("params="+params.toString());
this.apiService.flightSearch(params).subscribe({
  next:(response:any)=>{
    this.searchResults=response;
console.log(this.searchResults);
  },
  error:(err:any)=>{
    this.alertService.showError("Error Fetching Search Results","Error")
  }
})
  // this.http.get(this.baseUrl+'/flight', { params }).subscribe(
  //     response => console.log('Search results:', response),
  //     error => console.error('Error searching:', error)
  //   );
  }}
