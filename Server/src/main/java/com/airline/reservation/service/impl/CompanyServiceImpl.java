package com.airline.reservation.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.airline.reservation.entity.Company;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.repository.CompanyRepository;
import com.airline.reservation.service.CompanyService;
import com.airline.reservation.view.CompanyView;
@Service

public class CompanyServiceImpl implements CompanyService {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public CompanyView add(CompanyForm form) {
        String pas=passwordEncoder.encode( form.getPassword());
        form.setPassword(pas);
        return new CompanyView(companyRepository.save(new Company(form)));
    }
   

    @Override
    public List<CompanyView> list() {
        List<CompanyView> CompanyView = new ArrayList<>();
         List <Company> company = companyRepository.findAll();
         company.forEach(Company ->{
            CompanyView.add(new CompanyView(Company));
         });
         return CompanyView;
    }
    
    
    @Override
    @Transactional
    public void delete(Integer userId) throws NotFoundException {
        companyRepository.delete(
            companyRepository.findByUserId(userId)
                        .orElseThrow(NotFoundException::new)
        );
    }

}
