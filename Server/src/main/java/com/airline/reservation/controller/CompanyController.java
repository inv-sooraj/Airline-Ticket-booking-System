package com.airline.reservation.controller;

import java.io.IOException;
import java.security.Principal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.airline.reservation.entity.Company;
import com.airline.reservation.form.CompanyForm;
import com.airline.reservation.service.CompanyService;
import com.airline.reservation.view.CompanyView;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService CompanyService;

    // Add companies
    @PostMapping
    public CompanyView add(@Valid @RequestBody CompanyForm form) {
        return CompanyService.add(form);
    }
    
    //  list of All companies
    @GetMapping
    public List<CompanyView> list(Principal p) {
        return CompanyService.list();
    }

    // delete
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Integer userId) {
        CompanyService.delete(userId);
    }


    // CSV Export 

    @GetMapping("/export")
    public void Exportcsv(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("text/csv");
        java.text.DateFormat datefFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = datefFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=DataExport_" + currentDateTime + ".csv";
        httpServletResponse.setHeader(headerKey, headerValue);
        List<CompanyView>companies =CompanyService.list();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(httpServletResponse.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = { "Id", "Company Name", "Company Contact", "email", "address" };
        String[] nameMapping = { "userId", "fullName", "phone", "email", "address" };
        csvWriter.writeHeader(csvHeader);
        for (CompanyView rent : companies) {

            csvWriter.write(rent, nameMapping);
        }
        csvWriter.flush();
        csvWriter.close();   
    }

    // Search

    @GetMapping("/search/pagenateds")
    public ResponseEntity<Page<Company>>getCompany(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "user_id") String sortBy) {
        System.out.println("paage size" + pageSize);
        Page<Company> list = CompanyService.getCompanySearch(keyword, pageNo - 1, pageSize, sortBy);
        return new ResponseEntity<Page<Company>>(list, new HttpHeaders(),
                HttpStatus.OK);

    }

    // edit Company
    @GetMapping("/{userId}")
    public CompanyView get(@PathVariable("userId") Integer userId) {
        return CompanyService.get(userId);
    }

    @PutMapping("/{userId}")
    public CompanyView update(
            @PathVariable("userId") Integer userId,
            @Valid @RequestBody CompanyForm form
    ) {
        return CompanyService.update(userId, form);
    }
    
}
