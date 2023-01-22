package com.airline.reservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import com.airline.reservation.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends Repository<Company,Integer> {
    
     Company save(Company company);

     List<Company>findAll();
     
     @Query(value="select * from user where role=3",nativeQuery = true)
     List<Company>findByrole();
     @Query(value="SELECT * FROM `user` WHERE full_name LIKE %?1% AND role=3",nativeQuery = true)
     Page<Company>findByName(String name,Pageable pageable);

     void delete(Company orElseThrow);

     Optional<Company> findByUserId(Integer userId);
}
