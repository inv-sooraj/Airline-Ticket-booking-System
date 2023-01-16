package com.airline.reservation.entity;
import java.util.Date;
// import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import jakarta.persistence.Temporal;
// import jakarta.persistence.TemporalType;
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
