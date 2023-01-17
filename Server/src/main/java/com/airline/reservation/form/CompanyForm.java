package com.airline.reservation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

public class CompanyForm {

    @Size(max = 30) 
    @NotBlank
    private String fullName;
    @Size(max = 30)
    @Email
    private String email;
    @Size(max = 255)
    @NotBlank
    @UniqueElements
    private String address;
    @Size(max = 30)
    @NotBlank
    private String password;

    
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    

    
}
