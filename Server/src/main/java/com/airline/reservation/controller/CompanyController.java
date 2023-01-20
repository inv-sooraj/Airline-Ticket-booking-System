package com.airline.reservation.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    

    @GetMapping
    public List<CompanyView> list(Principal p) {
        return CompanyService.list();
    }


    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        CompanyService.delete(userId);
    }


    
}
