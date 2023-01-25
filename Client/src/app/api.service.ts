import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl = environment.baseUrl;
  constructor(private httpClient: HttpClient) { }
  /** Method to get the accesstocken stored in the local storage */
  getAccessToken(): any {
    return localStorage.getItem('accessToken');
  }
  /** Method to create new user(signup a new user) */

  createUser(userForm: any) {
    return this.httpClient.post(this.baseUrl + '/' + 'users', userForm);

  }

  /** Method to login users */

  login(userForm: any) {

    return this.httpClient.post(this.baseUrl + '/' + 'login', userForm);

  }

  /** Method to create plane */

  createPlane(userForm: any) {
    return this.httpClient.post(this.baseUrl + '/' + 'airplane', userForm, this.getHeader());
  }

  /** Method to create flight */


  createFlight(userForm: any) {
    return this.httpClient.post(this.baseUrl + '/' + 'flight', userForm);
  }

  /** Method to check whether the email id entered by the user is unique or not */


  isEmailUnique(userForm: any) {
    return this.httpClient.get(this.baseUrl + '/' + 'email', userForm);
  }

  /** Method to get header details */


  getHeader(): any {
    return {
      headers: {
        Authorization: 'Airline ' + this.getAccessToken(),
      },

    };
  }

  /** Method to get the airplane details */


  getAirPlane() {
    return this.httpClient.get(this.baseUrl + '/' + 'airplane', this.getHeader());

  }

  /** Method to get the airplane details of a spacific id */


  getAirPlaneById(id: any) {
    return this.httpClient.get(this.baseUrl + '/' + 'airplane' + '/' + id, this.getHeader());

  }

  /** Method to call the putmapping api for aiplane edit */


  sendUpdatePlane(responseBody: any, airplaneId: any) {

    return this.httpClient.put(this.baseUrl + '/' + 'airplane' + '/' + airplaneId, responseBody, this.getHeader());

  }

  /** Method to delete(soft delete) the plane */


  deletePlane(ids: any) {
    let params = new HttpParams()
      .set('ids', ids);
    return this.httpClient.delete(this.baseUrl + '/' + 'airplane' + '?' + 'ids' + '=' + ids, this.getHeader())
  };

  /** Method to get the users of role 2(company users) */

  getCompany() {
    return this.httpClient.get(this.baseUrl + '/' + 'users' + '/' + 'GetCompany', this.getHeader());

  }
  getPlaneByCompany(id:any){
    return this.httpClient.get(this.baseUrl + '/' + 'airplane' + '/' + 'getbyCompany'+'/'+ id, this.getHeader());

  }
}

