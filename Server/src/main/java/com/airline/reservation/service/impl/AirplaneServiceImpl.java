/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.service.impl;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.form.AirplaneForm;
import com.airline.reservation.repository.AirplaneRepository;
import com.airline.reservation.security.util.SecurityUtil;
import com.airline.reservation.service.AirplaneService;
import com.airline.reservation.view.AirplaneListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lakshmimohan
 */
@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;
    
    @Override
    public AirplaneListView add(AirplaneForm form) {
               return new AirplaneListView(airplaneRepository.save(new Airplane(form,SecurityUtil.getCurrentUserId(),Airplane.Status.ACTIVE.value )));
    }
    
}
