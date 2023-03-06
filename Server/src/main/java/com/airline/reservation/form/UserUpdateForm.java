/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.form;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author lakshmimohan
 */
public class UserUpdateForm {

    @NotBlank(message = "1001")
    @Size(min = 4, max = 20)
    private String fullName;

    @NotBlank(message = "1003")
    @Email(message = "1002")
    private String email;

    private Date dob;

    @Size(min = 8, max = 8, message = "1005")
    private String passportNumber;

    private String address;

    @NotBlank(message = "1004")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "1004")
    @Size(max = 11, message = "1004")
    private String phone;

    @Size(max = 18, message = "1006")
    private String city;

    @Size(max = 18, message = "1007")
    private String country;

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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

}
