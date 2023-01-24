package com.airline.reservation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.airline.reservation.json.Json;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private   Integer flightId;
    @Column(nullable = false, length =45)
    private   Integer aeroplaneId;
    @Column(nullable = false, length =45)
    private   String flightNumber;
    @Column(nullable = false, length =45)
    private   String departure;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private  Date depDateTime;
    @Column(nullable = false, length =45)
    private   String destination;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private  Date destDateTime;
    @Column(nullable = false,columnDefinition = "TINYINT")
    private   Integer deleteFlag;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private   Date updateDate;
    @Column(nullable = false)
    @Json.DateTimeFormat
    private  Date createDate;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Airplane airplane;
}