import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  constructor(private http:HttpClient) { }
// company add service
  addcompany(data:any):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.post(environment.baseUrl + '/company',data,{headers:head_obj});
  }
// All company list 
  getCompanyList():Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.get(environment.baseUrl + '/company',  {headers:head_obj});
  }
  // CSV export service
  exportCompany(): Observable<Blob> {
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})

    return this.http.get(environment.baseUrl + '/company/export',{headers:head_obj,responseType: 'blob' });
  }
// Company Display on induvidual id base
  getcompanyById(userId:any):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.get(environment.baseUrl + '/company/'+ userId,{headers:head_obj});
  }
// company edit
  updatecompany(n:any,userId:any):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.put(environment.baseUrl + '/company/'+ userId,n,{headers:head_obj});
  }
  //flight list company base
  getFlightList(companyId:any):Observable<any>{
    console.log("asadasd",companyId);
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.get(environment.baseUrl + '/flight/companyname/'+companyId,{headers:head_obj});
  }
  // delete
  delete(a:any[]):Observable<any>{
    let tocken=localStorage.getItem('accesstoken')
    let  head_obj=new HttpHeaders({"Authorization":"Airline " + tocken})
    return this.http.delete(environment.baseUrl + '/company?delete='+a,{headers:head_obj});
  }
}
