package com.chansumeran.JobApp.company.impl;

import com.chansumeran.JobApp.company.Company;
import com.chansumeran.JobApp.company.CompanyRepository;
import com.chansumeran.JobApp.company.CompanyRequestDto;
import com.chansumeran.JobApp.company.CompanyService;
import com.chansumeran.JobApp.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, CompanyRequestDto companyRequest) {
        Company companyToUpdate = companyRepository.findById(id).orElse(null);

        if (companyToUpdate != null) {

            companyToUpdate.setName(companyRequest.getName());
            companyToUpdate.setDescription(companyRequest.getDescription());

            companyRepository.save(companyToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(CompanyRequestDto companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setDescription(companyRequest.getDescription());

        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {

        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
