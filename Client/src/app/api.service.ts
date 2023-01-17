import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl = environment.baseUrl;
  constructor(private httpClient:HttpClient) { }


  createUser(userForm:any)
  {
    return this.httpClient.post(this.baseUrl+'/'+'user',userForm);

  }
  login(userForm:any){

    return this.httpClient.post(this.baseUrl+'/'+'login',userForm);
    
  }
  createPlane(userForm:any)
  {
    return this.httpClient.post(this.baseUrl+'/'+'plane',userForm);
  }
  createFlight(userForm:any)
{
  return this.httpClient.post(this.baseUrl+'/'+'flight',userForm);
}
isEmailUnique(userForm:any){
  return this.httpClient.get(this.baseUrl+'/'+'email',userForm);
}
}

