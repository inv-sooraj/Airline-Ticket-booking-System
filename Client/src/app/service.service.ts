import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }

  // addcompany(data:any):Observable<any>{
  //   let tocken=localStorage.getItem('')
  //   let  head_obj=new HttpHeaders({"Authorization":"" + tocken})
  //   return this.http.post(environment.baseUrl + '',data,{headers:head_obj});
  // }
}
