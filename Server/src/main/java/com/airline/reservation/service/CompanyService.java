package com.airline.reservation.service;

import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.view.CompanyView;

public interface CompanyService {

    CompanyView add(CompanyForm form);
    
}
