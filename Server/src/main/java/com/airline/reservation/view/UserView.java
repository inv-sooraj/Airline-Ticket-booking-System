package com.airline.reservation.view;
import java.util.Date;

import com.airline.reservation.entity.User;
import com.airline.reservation.json.Json;
public class UserView {
   
  
private final Integer userId;

private final String fullName;

private final String email;
@Json.DateFormat
private final Date dob;

private final String passport_number;
private final String address;

private final String phone;

private final String city;
private final String country;

private final Integer status;
private final Integer role;
@Json.DateTimeFormat
private final Date updateDate;
@Json.DateTimeFormat
private final Date createDate; 
public UserView(User user) {
   this.userId = user.getUserId();
   this.fullName=user.getFullName();
   this.email=user.getEmail();
   this.dob=user.getDob();
   this.passport_number=user.getPassport_number();
   this.address=user.getAddress();
   this.phone=user.getPhone();
   this.city=user.getCity();
   this.country=user.getCountry();
   this.createDate=user.getCreateDate();
   this.role=user.getRole();
   this.updateDate=user.getUpdateDate();
   this.status=user.getStatus();

}
public Integer getUserId() {
    return userId;
}
public String getFullName() {
    return fullName;
}
public String getEmail() {
    return email;
}
public Date getDob() {
    return dob;
}
public String getPassport_number() {
    return passport_number;
}
public String getAddress() {
    return address;
}
public String getPhone() {
    return phone;
}
public String getCity() {
    return city;
}
public String getCountry() {
    return country;
}
public Integer getStatus() {
    return status;
}
public Integer getRole() {
    return role;
}
public Date getUpdateDate() {
    return updateDate;
}
public Date getCreateDate() {
    return createDate;
}

}
