import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }

  addcompany(data:any):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.post(environment.baseUrl + '/company',data,{headers:head_obj});
  }

  getCompanyList():Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.get(environment.baseUrl + '/company',  {headers:head_obj});
  }
  exportCompany(): Observable<Blob> {
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})

    return this.http.get(environment.baseUrl + '/company/export',{headers:head_obj,responseType: 'blob' });
  }

  getcompanyById(userId:any):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.get(environment.baseUrl + '/company/'+ userId,{headers:head_obj});
  }

  updatecompany(n:any,userId:any):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.put(environment.baseUrl + '/company/'+ userId,n,{headers:head_obj});
  }
  
}
