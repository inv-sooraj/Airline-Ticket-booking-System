package com.airline.reservation.view;

import com.airline.reservation.entity.Company;

public class CompanyView {

    private Integer userId;
    private String fullName;
    private String email;
    private String address;
    private String password;
     
    public CompanyView(Company company){
        this.userId = company.getUserId();
        this.fullName = company.getFullName();
        this.email = company.getEmail();
        this.address = company.getAddress();
        this.password = company.getPassword();
    }

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
