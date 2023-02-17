package com.airline.reservation.form;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.airline.reservation.form.validation.Password;

public class UserForm {

    @NotBlank(message = "1001")
    @Size(min = 4, max = 20)
    private String fullName;

    @NotBlank(message = "1003")
    @Email(message = "1002")
    private String email;

    @Password(message = "1003")
    private String password;
    private Date dob;

    @Size(min = 8, max = 8)
    private String passportNumber;

    private String address;

    @NotBlank(message = "1004")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "1004")
    @Size(max = 11, message = "1004")
    private String phone;

    @Size(max = 18)
    private String city;

    @Size(max = 18)
    private String country;

    @NotNull
    private Integer status;

    @NotNull
    private Integer role;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

}
