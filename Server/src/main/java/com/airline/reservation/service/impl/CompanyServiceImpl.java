package com.airline.reservation.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // Add Company
    public CompanyView add(CompanyForm form) {
        String pas=passwordEncoder.encode( form.getPassword());
        form.setPassword(pas);
        return new CompanyView(companyRepository.save(new Company(form)));
    }
   
    // List Company
    @Override
    public List<CompanyView> list() {
        List<CompanyView> CompanyView = new ArrayList<>();
         List <Company> company = companyRepository.findByrole();
         company.forEach(Company ->{
            CompanyView.add(new CompanyView(Company));
         });
         return CompanyView;
    }
    

    // Delete Company
    @Override
    @Transactional
    public void delete(Integer userId) throws NotFoundException {
        companyRepository.delete(
            companyRepository.findByUserId(userId)
                        .orElseThrow(NotFoundException::new)
        );
    }

    // Search and Pagination
    @Override
    public Page<Company>getCompanySearch(String keyword, Integer pageNo,Integer pageSize,String sortBy){
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
    System.out.println(keyword);

    Page<Company> pagedResult = companyRepository.findByName(keyword, paging);
    return pagedResult;
    }

    
    // Detail View
    @Override
    public CompanyView get(Integer UserId) throws NotFoundException{
        return companyRepository.findByUserId(UserId).map((Company)->{
            return new CompanyView(Company);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public CompanyView update(Integer userId, CompanyForm form) throws NotFoundException {
        return companyRepository.findByUserId(userId)
                .map((company) -> {
                    return new CompanyView(companyRepository.save(company.update(form)));
                }).orElseThrow(NotFoundException::new);
    }
}
