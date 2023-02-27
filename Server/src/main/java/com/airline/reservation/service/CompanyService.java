package com.airline.reservation.service;

import com.airline.reservation.entity.Company;
import com.airline.reservation.exception.NotFoundException;
import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.view.CompanyView;
import com.airline.reservation.view.UserView;
import java.util.List;
import org.springframework.data.domain.Page;

public interface CompanyService {

    UserView add(CompanyForm form);

    List<CompanyView> list();

    void delete(Integer bookId) throws NotFoundException;

    Page<Company> getCompanySearch(String keyword, Integer pageNo, Integer pageSize, String sortBy);

    // detail view
    CompanyView get(Integer userId) throws NotFoundException;

    CompanyView update(Integer userId, CompanyForm form) throws NotFoundException;
}
