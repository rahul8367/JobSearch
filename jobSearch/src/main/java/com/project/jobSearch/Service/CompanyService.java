package com.project.jobSearch.Service;

import com.project.jobSearch.entity.Company;
import com.project.jobSearch.entity.Job;

import java.util.List;

public interface CompanyService {
    List<Object> getAllCompany();

    Company add(Company comp);

    Company update(Company comp);

    String delete(Long id);

    List<Job> getAllJobs();
    Company getById(Long id);
}
