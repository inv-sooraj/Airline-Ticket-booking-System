package com.airline.reservation.entity;
import java.util.Date;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.airline.reservation.json.Json;
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer userId;
    @Column(nullable = false, length =50)
    private   String fullName;
    @Column(nullable = false, length =50)
    private   String email;
    @Column(nullable = true, columnDefinition = "DATE")
    private   Date dob;
    @Column(nullable = true, length =50)
    private   String passport_number;
    @Column(nullable = true)
    private   String address;
    @Column(nullable = false, length =20)
    private   String phone;
    @Column(nullable = true, length =50)
    private   String city;
    @Column(nullable = true, length =50)
    private   String country;
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
    public String getPassport_number() {
        return passport_number;
    }
    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
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
}
