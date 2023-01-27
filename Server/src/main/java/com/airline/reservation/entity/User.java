package com.airline.reservation.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.airline.reservation.json.Json;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer userId;
    @Column(nullable = false, length =50)
    private   String fullName;
    @Column(nullable = false, length =50,unique=true)
    private   String email;
    @Column(nullable = true, columnDefinition = "DATE")
    private   Date dob;
    @Column(nullable = true, length =50)
    private   String passportNumber;
    @Column(nullable = true)
    private   String address;
    @Column(nullable = false, length =20)
    private   String phone;
    @Column(nullable = true, length =50)
    private   String city;
    @Column(nullable = true, length =50)
    private   String country;
    @Column(nullable = false)
    private   String password;
   
    @Column(nullable = false,columnDefinition = "TINYINT")
    private   Integer status;
    @Column(nullable = false,columnDefinition = "TINYINT")
    private   Integer role;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private   Date updateDate;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private  Date createDate;
public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getRole() {
        return role;
    }
    public void setRole(Integer role) {
        this.role = role;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }
    public User(String fullName, String email, String phone,String password,Integer role,Integer status,String address,String passportNumber,String city,String country,Date dob) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password=password;
        this.role=role;
        this.status=status;
        this.address=address;
        this.passportNumber=passportNumber;
       
        this.country=country;
        this.city=city;
        Date dt = new Date();
        this.createDate = dt;
        this.updateDate = dt;
        this.dob=dob;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
