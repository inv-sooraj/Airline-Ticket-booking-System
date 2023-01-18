package com.airline.reservation.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Company;
import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.repository.CompanyRepository;
import com.airline.reservation.service.CompanyService;
import com.airline.reservation.view.CompanyView;
@Service

public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public CompanyView add(CompanyForm form) {
        return new CompanyView(companyRepository.save(new Company(form)));
    }
}
