package com.airline.reservation.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.airline.reservation.json.Json;
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int seatId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Flight flight;
    @Column(length=45,nullable = false)
    private Integer seatType;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = false)
    private Integer price;
    @Column(columnDefinition="TINYINT",nullable = false)
    private Integer deleteFlag;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private   Date updateDate;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private  Date createDate; 
}
