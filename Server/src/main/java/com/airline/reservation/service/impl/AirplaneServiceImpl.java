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

    //method to add airplane
    @Override
    public AirplaneListView addPlane(AirplaneForm form) {
        System.out.println("user id =" + SecurityUtil.getCurrentUserId());
        return new AirplaneListView(airplaneRepository.save(new Airplane(form, SecurityUtil.getCurrentUserId(), Airplane.Status.ACTIVE.value)));
    }

    //method to list all plane details
    @Override
    public Collection<Airplane> listPlanes() {

        return airplaneRepository.findAllByStatus(Airplane.Status.ACTIVE.value);

    }

    //method to get details of a particular airplane id
    @Override
    public AirplaneListView getPlaneById(Integer airplaneId) throws NotFoundException {

        return airplaneRepository.findByAirplaneId(airplaneId)
                .map((Airplane) -> {
                    return new AirplaneListView(Airplane);
                }).orElseThrow(NotFoundException::new);
    }

    //method to update details of a particular ariplaneid
    @Override
    @Transactional
    public AirplaneListView updatePlane(Integer airplaneId, AirplaneForm form) throws NotFoundException {
        return airplaneRepository.findByAirplaneId(airplaneId)
                .map((Airplane) -> {
                    return new AirplaneListView(airplaneRepository.save(Airplane.update(form)));
                }).orElseThrow(NotFoundException::new);
    }

    //method to delete airplane details of listed ids(soft delete)
    @Override
    @Transactional
    public void deletePlaneByIds(List<Integer> integers) {
        airplaneRepository.softDeleteAllIds(integers);
    }

    //method to get airplane details by particular userid(company)
    @Override
    public Collection<Airplane> getDataByUser(Integer userId) {

        return airplaneRepository.findAllByUserUserIdAndStatus(userId, Airplane.Status.ACTIVE.value);

    }

}
