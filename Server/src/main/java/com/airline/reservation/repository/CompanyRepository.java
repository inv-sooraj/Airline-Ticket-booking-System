package com.airline.reservation.repository;

import org.springframework.data.repository.Repository;
import com.airline.reservation.entity.Company;

public interface CompanyRepository extends Repository<Company,Integer> {
    
     Company save(Company company);
}
