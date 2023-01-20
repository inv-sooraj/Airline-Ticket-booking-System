package com.airline.reservation.service;

import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.view.CompanyView;

import java.util.List;

public interface CompanyService {

    CompanyView add(CompanyForm form);

    List<CompanyView>list();

    void delete(Integer bookId) throws NotFoundException;
    
}
