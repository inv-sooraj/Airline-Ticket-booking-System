import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingServiceService {
  header:any;
  DownloadURL:any;
  constructor(private http : HttpClient) { 
    this.DownloadURL="http://localhost:9091/bookings/download";
  }
  download(){
    console.log("in service")
    // let token=localStorage.getItem('token')

console.log(" URL = "+this.DownloadURL);
     this.http.get(this.DownloadURL);
  }
}
