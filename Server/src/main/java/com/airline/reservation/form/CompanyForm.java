package com.airline.reservation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class CompanyForm {

    @Size(min = 5, max = 50)
    private String fullName;
    @Email
    @Size(max = 30)
    private String email;
    @Size(min = 10, max = 11)
    private String phone;
    private String address;
    private String password;
    private Integer role;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

}
