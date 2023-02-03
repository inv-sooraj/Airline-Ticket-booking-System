import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class BookingServiceService {
  
  baseUrl:any
  constructor(private http : HttpClient) { 
    this.DownloadURL="http://localhost:9091/bookings/download";
  this.baseUrl="http://localhost:9091/bookings"
  }
  header:any;
  DownloadURL:any;
  // baseUrl = environment.baseUrl;
  
  deletebooking(ids: any) {
    let params = new HttpParams()
      .set('ids', ids);
      console.log(this.baseUrl + '?' + 'ids' + '=' + ids);
    return this.http.delete(this.baseUrl + '?' + 'ids' + '=' + ids, this.getHeader())

  }
  changeStatus(id: any, status: any) {
    return this.http.put(this.baseUrl + '/changeStatus/'+id + '/' + status, this.getHeader())
  }
   getHeader(): any {
    return {
      headers: {
        Authorization: 'Airline ' + this.getAccessToken(),
      },

    };
  }
  getAccessToken(): any {
    return localStorage.getItem('accessToken');
  }

  download(){
    console.log("in service")
    // let token=localStorage.getItem('token')

console.log(" URL = "+this.DownloadURL);
     this.http.get(this.DownloadURL);
  }
  getBooking() {
    console.log(this.baseUrl + "/status/1");
    return this.http.get(this.baseUrl + '/status/1', this.getHeader());
     
     
     }
}
