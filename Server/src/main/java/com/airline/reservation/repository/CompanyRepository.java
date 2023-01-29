package com.airline.reservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import com.airline.reservation.entity.Company;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

public interface CompanyRepository extends Repository<Company,Integer> {
    
     // Company Add
     Company save(Company company);

     // List All In the Table
     List<Company>findAll();
     
     // List All Companies
     @Query(value="select * from user where role=3 and status=1",nativeQuery = true)
     List<Company>findByrole();

     // Search and pagination
     @Query(value="SELECT * FROM `user` WHERE full_name LIKE %?1% AND role=3",nativeQuery = true)
     Page<Company>findByName(String name,Pageable pageable);

     // Deletion One by one
     void delete(Company orElseThrow);
     

     // detail View
     Optional<Company> findByUserId(Integer userId);

     // multiple delete
     @Modifying
     @Transactional
     @Query("update  User c set c.status = 0 where c.userId in(:integers)")
     void softdelete(List<Integer> integers);
 
}
