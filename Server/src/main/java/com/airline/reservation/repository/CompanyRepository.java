package com.airline.reservation.repository;

import org.springframework.data.repository.Repository;
import com.airline.reservation.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends Repository<Company,Integer> {
    
     Company save(Company company);

     List<Company>findAll();

     void delete(Company orElseThrow);

     Optional<Company> findByUserId(Integer userId);
}
