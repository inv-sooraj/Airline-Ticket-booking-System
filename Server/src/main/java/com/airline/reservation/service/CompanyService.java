package com.airline.reservation.service;

import com.airline.reservation.entity.Company;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.view.CompanyView;

import java.util.List;

import org.springframework.data.domain.Page;

public interface CompanyService {

    // Add Company
    CompanyView add(CompanyForm form);

    // Lisrt of All 
    List<CompanyView>list();

    // Single Delete
    void delete(Integer bookId) throws NotFoundException;
    
    // Search
    Page<Company>getCompanySearch(String keyword, Integer pageNo,Integer pageSize,String sortBy);

    // detail view
    CompanyView get(Integer userId) throws NotFoundException;
    
    // Edit
    CompanyView update(Integer userId, CompanyForm form) throws NotFoundException;

    // delete multiple
    public void deleteAllById(List<Integer> integers);

}
