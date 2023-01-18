/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.controller;

import com.airline.reservation.form.AirplaneForm;
import com.airline.reservation.service.AirplaneService;
import com.airline.reservation.view.AirplaneListView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lakshmimohan
 */
@RestController
@RequestMapping("/airplane")
public class AirplaneController {
    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    public AirplaneListView add(@Valid @RequestBody AirplaneForm form) {
        return airplaneService.add(form);
    } 
}
