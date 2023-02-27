/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 *
 * @author lakshmimohan
 */
public class CompanyUpdateForm {
    @Size(min = 5, max = 50)
    private String fullName;
    @Email
    @Size(max = 30)
    private String email;
    @Size(min = 10, max = 11)
    private String phone;
    private String address;
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

    

}
