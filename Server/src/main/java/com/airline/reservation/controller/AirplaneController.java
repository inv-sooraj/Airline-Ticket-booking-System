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
    public AirplaneListView add(@Valid @RequestBody AirplaneForm form) {

        return airplaneService.add(form);
    }

    @GetMapping
    public Collection<Airplane> list(Principal p) {
        return airplaneService.list();
    }

    @GetMapping("/{airplaneId}")
    public AirplaneListView get(@PathVariable("airplaneId") Integer airplaneId) {
        return airplaneService.get(airplaneId);
    }

    @PutMapping("/{airplaneId}")
    public AirplaneListView update(
            @PathVariable("airplaneId") Integer airplaneId,
            @Valid @RequestBody AirplaneForm form) {
        return airplaneService.update(airplaneId, form);
    }

    @DeleteMapping
    public void delete(@RequestParam("ids") ArrayList<Integer> ids) {
        System.out.println("deleting");
        airplaneService.deleteAllBYIds(ids);
    }

    @RequestMapping(value = "/getbyCompany/{userId}", method = RequestMethod.GET)
    public Collection<Airplane> getByCompany(@PathVariable("userId") Integer userId) {
        return airplaneService.getDataByUser(userId);
    }
}
