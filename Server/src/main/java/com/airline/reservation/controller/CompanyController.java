package com.airline.reservation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.service.CompanyService;
import com.airline.reservation.view.CompanyView;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService CompanyService;

    @PostMapping
    public CompanyView add(@Valid @RequestBody CompanyForm form) {
        return CompanyService.add(form);
    }
    

}
