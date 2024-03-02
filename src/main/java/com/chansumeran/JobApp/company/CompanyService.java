package com.chansumeran.JobApp.company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();

    boolean updateCompany(Long id, Company company);

    void createCompany(CompanyRequestDto company);

    boolean deleteCompany(Long id);

    Company getCompanyById(Long id);
}
