import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl = environment.baseUrl;
  constructor(private httpClient:HttpClient) { }

  getAccessToken():any{
    return localStorage.getItem('accessToken');
  }
  createUser(userForm:any)
  {
    return this.httpClient.post(this.baseUrl+'/'+'users',userForm);

  }
  login(userForm:any){

    return this.httpClient.post(this.baseUrl+'/'+'login',userForm);
    
  }
  createPlane(userForm:any)
  {
    return this.httpClient.post(this.baseUrl+'/'+'airplane',userForm,this.getHeader());
  }
  createFlight(userForm:any)
{
  return this.httpClient.post(this.baseUrl+'/'+'flight',userForm);
}
isEmailUnique(userForm:any){
  return this.httpClient.get(this.baseUrl+'/'+'email',userForm);
}
getHeader(): any {
  return {
    headers: { Authorization: 'Airline ' + this.getAccessToken(),
   },
 
  };
}
getAirPlane()
{
  return this.httpClient.get(this.baseUrl+'/'+'airplane',this.getHeader());
  
}
getAirPlaneById(id:any)
{
  return this.httpClient.get(this.baseUrl+'/'+'airplane'+'/'+id,this.getHeader());
  
}
sendUpdatePlane(responseBody:any,airplaneId:any){

  return this.httpClient.put(this.baseUrl+'/'+'airplane'+'/'+airplaneId,responseBody,this.getHeader());

}
deletePlane(ids:any){
  let params = new HttpParams()
    .set('ids', ids);
 return this.httpClient.delete(this.baseUrl +'/'+'airplane'+'?'+'ids'+'='+ids,this.getHeader())
    };
}

