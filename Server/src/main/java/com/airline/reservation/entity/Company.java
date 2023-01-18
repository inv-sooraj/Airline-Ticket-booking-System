package com.airline.reservation.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.airline.reservation.form.CompanyForm;

import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name= "user")
public class Company {

    public static enum Status{
        DELETE((byte)1),
        ACTIVE((byte)0);

        public final byte value; 
            
        private Status(byte value) {
            this.value = value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String password;
    private Integer role;
    private byte status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public Company(Integer userId,String fullName,String email,String address,String password,String phone,Integer role){
    this.userId = userId;
    this.fullName = fullName;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.password = password;
    this.role = role;

    this.status = Status.ACTIVE.value;


    Date dt = new Date();
    this.createDate = dt;
    this.updateDate = dt;
    
    }



    public Company(CompanyForm form) {
        this.fullName =form.getFullName();
        this.email =form.getEmail();
        this.phone =form.getPhone();
        this.address =form.getAddress();
        this.password =form.getPassword();
        this.role =form.getRole(); 
        
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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
