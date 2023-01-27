/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.airline.reservation.service.impl;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.AirplaneForm;
import com.airline.reservation.repository.AirplaneRepository;
import com.airline.reservation.security.util.SecurityUtil;
import com.airline.reservation.service.AirplaneService;
import com.airline.reservation.view.AirplaneListView;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
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

    @Override
    public Collection<Airplane> list() {
        
//            return airplaneRepository.findAllByStatusAndUserUserId(Airplane.Status.ACTIVE.value,SecurityUtil.getCurrentUserId());
              return airplaneRepository.findAllByStatus(Airplane.Status.ACTIVE.value);


    }
    

    @Override
    public AirplaneListView get(Integer airplaneId)throws NotFoundException {
        
        return airplaneRepository.findByAirplaneId(airplaneId)
                .map((Airplane) -> {
                    return new AirplaneListView(Airplane);
                }).orElseThrow(NotFoundException::new);
    }
    @Override
    @Transactional
    public AirplaneListView update(Integer airplaneId, AirplaneForm form) throws NotFoundException {
        return airplaneRepository.findByAirplaneId(airplaneId)
                .map((Airplane) -> {
                    return new AirplaneListView(airplaneRepository.save(Airplane.update(form)));
                }).orElseThrow(NotFoundException::new);
    }
    @Override
    @Transactional
    public void deleteAllBYIds(List<Integer> integers) {
        airplaneRepository.softDeleteAllIds(integers);
    }

    @Override
    public Collection<Airplane> getDataByUser(Integer userId) {
        
        return airplaneRepository.findAllByUserUserIdAndStatus(userId,Airplane.Status.ACTIVE.value);

    }


    }

