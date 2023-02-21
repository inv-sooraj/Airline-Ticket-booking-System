import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: "root",
})
export class ApiService {
  getTwoRandom() {
    return this.httpClient.get(this.baseUrl+"/flight/getTwoRandom",this.getHeader());
  }
  getSeatPrice(seatId: any,index:any) {
    console.log(this.baseUrl+'/seat/getPrice');
   return this.httpClient.get(this.baseUrl+'/seat/getPrice/'+seatId);
  }
  getFlightDetail(flightId: any) {
    return this.httpClient.get(this.baseUrl+"/flight/"+flightId,this.getHeader());
  }
  flightSearch(params: HttpParams) {
    

 return this.httpClient.get(this.baseUrl+'/flight/search', { params });
  }

  getSeatIdAndTypeId(flightId:any){
    return this.httpClient.get(this.baseUrl+'/seat/'+flightId);
  }
  searchFlight(formValue: any) {
   return this.httpClient.get(this.baseUrl+"/flight")
  }
  
  baseUrl = environment.baseUrl;
  constructor(private httpClient: HttpClient) {}
  /** Method to get the accesstocken stored in the local storage */
  getAccessToken(): any {
    return localStorage.getItem("accessToken");
  }
  /** Method to create new user(signup a new user) */
  createUser(userForm: any) {
    console.log(userForm);

    return this.httpClient.post(this.baseUrl + "/" + "users/signup", userForm);
  }
  getAllUsers() {
    return this.httpClient.get(
      this.baseUrl + "/users/GetUsers",
      this.getHeader()
    );
  }
  getRandom(){
    
    return this.httpClient.get(this.baseUrl+"/flight/getRandom",this.getHeader());
  }
 
  getUserBookingList() {
    console.log(this.baseUrl + "/bookings/getById/1");
    return this.httpClient.get(this.baseUrl + "/bookings/getById/1",this.getHeader());
  }
  updateUser(userid: any, updateForm: any) {
    return this.httpClient.put(
      this.baseUrl + "/" + "users/" + userid,
      updateForm,
      this.getHeader()
    );
  }

  deleteUser(id: any) {
    return this.httpClient.put(
      this.baseUrl + "/" + "users/changeStatus/" + id,
      this.getHeader()
    );
  }
  /** Method to login users */

  login(userForm: any) {
    return this.httpClient.post(this.baseUrl + "/" + "login", userForm);
  }

  /** Method to create plane */

  createPlane(userForm: any) {
    return this.httpClient.post(
      this.baseUrl + "/" + "airplane",
      userForm,
      this.getHeader()
    );
  }

  /** Method to create flight */

  createFlight(userForm: any) {
    return this.httpClient.post(
      this.baseUrl + "/" + "flight",
      userForm,
      this.getHeader()
    );
  }

  /** Method to check whether the email id entered by the user is unique or not */

  isEmailUnique(userForm: any) {
    return this.httpClient.get(this.baseUrl + "/" + "email", userForm);
  }

  /** Method to get header details */

  getHeader(): any {
    return {
      headers: {
        Authorization: "Airline " + this.getAccessToken(),
      },
    };
  }

  /** Method to get the airplane details */

  getAirPlane() {
    return this.httpClient.get(
      this.baseUrl + "/" + "airplane",
      this.getHeader()
    );
  }

  /** Method to get the airplane details of a spacific id */

  getAirPlaneById(id: any) {
    return this.httpClient.get(
      this.baseUrl + "/" + "airplane" + "/" + id,
      this.getHeader()
    );
  }

  /** Method to call the putmapping api for aiplane edit */

  sendUpdatePlane(responseBody: any, airplaneId: any) {
    return this.httpClient.put(
      this.baseUrl + "/" + "airplane" + "/" + airplaneId,
      responseBody,
      this.getHeader()
    );
  }

  /** Method to delete(soft delete) the plane */

  deletePlane(ids: any) {
    let params = new HttpParams().set("ids", ids);

    return this.httpClient.delete(
      this.baseUrl + "/" + "airplane" + "?" + "ids" + "=" + ids,
      this.getHeader()
    );
  }
  /* multi deletion of flights */
  deleteFlight(ids:any){
    

    return this.httpClient.delete(
      this.baseUrl + '/flight?ids='+ ids,
      this.getHeader()
    );
  }
  /** Method to get the users of role 2(company users) */

  getCompany() {
    return this.httpClient.get(
      this.baseUrl + "/" + "users" + "/" + "GetCompany",
      this.getHeader()
    );
  }

  /**Method to get the plane details by spacific companyid */

  getPlaneByCompany(id: any) {
    return this.httpClient.get(
      this.baseUrl + "/" + "airplane" + "/" + "getbyCompany" + "/" + id,
      this.getHeader()
    );
  }

  /**Method to get user details by id */

  getUserById(id: any) {
    return this.httpClient.get(
      this.baseUrl + "/" + "users" + "/" + id,
      this.getHeader()
    );
  }

  /**method to edit the user form */

  editUserData(requestbody: any) {
    return this.httpClient.put(
      this.baseUrl + "/" + "users",
      requestbody,
      this.getHeader()
    );
  }

  /**change password */

  changePasswd(requestbody: any) {
    console.log("Form Data" + requestbody);
    return this.httpClient.put(
      this.baseUrl + "/" + "users" + "/" + "changePwd",
      requestbody,
      this.getHeader()
    );
  }

  /**Method to get booking details by id */

  bookingDetailsById(id: any) {
    console.log(this.baseUrl + "/" + "bookings" + "/" + id + "/" + "1")
    return this.httpClient.get(
      this.baseUrl + "/" + "bookings" + "/" + id + "/" + "1",
      this.getHeader()
    );
  }

  cancelBooking(id: any,reason:any) {
    const cancelStatus=3;
    console.log( this.baseUrl + "/bookings/cancelBooking/" + id +'/'+reason +cancelStatus)
    return this.httpClient.put(
      this.baseUrl + "/bookings/cancelBooking/" + id +'/'+reason +'/'+cancelStatus,
      this.getHeader()
    );
  }

  //method to get all flight details(for admin)
  getAllFlight(){
    return this.httpClient.get(
      this.baseUrl + '/flight/findAll',
      this.getHeader()
    );
    
  }

  //Method to get flight details of a particular company(userid)

  getFlightByCompany(id:any){
    return this.httpClient.get(
      this.baseUrl + '/flight/findAll/'+id,
      this.getHeader()
    );
    
  }

  /* method to get flight details by id(flightid) */
  getPlaneDataById(flightId:any){
    return this.httpClient.get(
      this.baseUrl + '/flight/'+flightId,
      this.getHeader()
    );
  }

  /* Edit flight*/
  
  sendUpdateflight(flightform:any,flightId:any){
    return this.httpClient.put(
      this.baseUrl + '/flight/'+flightId,flightform,
      this.getHeader()
    );
  }
}
