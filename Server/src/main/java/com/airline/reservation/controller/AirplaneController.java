package com.airline.reservation.controller;

import com.airline.reservation.entity.Airplane;
import com.airline.reservation.form.AirplaneForm;
import com.airline.reservation.service.AirplaneService;
import com.airline.reservation.view.AirplaneListView;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public AirplaneListView addAirplane(@Valid @RequestBody AirplaneForm form) {

        return airplaneService.addPlane(form);
    }

    //Method to list all airplane details
    @GetMapping
    public Collection<Airplane> listAllPlanes(Principal p) {
        return airplaneService.listPlanes();
    }

    //method to get details of a particular airplane id
    @GetMapping("/{airplaneId}")
    public AirplaneListView getPlaneDetails(@PathVariable("airplaneId") Integer airplaneId) {
        return airplaneService.getPlaneById(airplaneId);
    }

    //method to update details od a particular airplane id
    @PutMapping("/{airplaneId}")
    public AirplaneListView updateAirplane(
            @PathVariable("airplaneId") Integer airplaneId,
            @Valid @RequestBody AirplaneForm form) {
        return airplaneService.updatePlane(airplaneId, form);
    }

    //Method to delete multiple airplane details
    @DeleteMapping
    public void deleteAirplane(@RequestParam("ids") ArrayList<Integer> ids) {
        System.out.println("deleting");
        airplaneService.deletePlaneByIds(ids);
    }

    //method to get airplane details of particular company id(user id)
    @RequestMapping(value = "/getbyCompany/{userId}", method = RequestMethod.GET)
    public Collection<Airplane> getAirplaneByCompany(@PathVariable("userId") Integer userId) {
        return airplaneService.getDataByUser(userId);
    }
}
