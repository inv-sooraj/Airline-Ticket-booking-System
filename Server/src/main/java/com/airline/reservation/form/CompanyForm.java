package com.airline.reservation.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.airline.reservation.form.validation.Password;

public class CompanyForm {
    @NotBlank
    @Size(min = 4, max = 20)
    private String fullName;
    @NotBlank
    @Email
    @Size( max = 30)
    private String email;
    @NotBlank
    @Size(max = 11)
    private String phone;
    private String address;
    @Password
    private String password;
    @NotNull
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
