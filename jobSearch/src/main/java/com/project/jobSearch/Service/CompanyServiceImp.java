package com.project.jobSearch.Service;

import com.project.jobSearch.entity.Company;
import com.project.jobSearch.entity.Job;
import com.project.jobSearch.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyServiceImp implements CompanyService{
    @Autowired
    private CompanyRepo companyRepository;
    @Override
    public List<Object> getAllCompany() {
        return null;
    }

    @Override
    public Company add(Company comp) {
        return companyRepository.save(comp);
    }

    @Override
    public Company update(Company comp) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public List<Job> getAllJobs() {
        return null;
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.getById(id);
    }
}
