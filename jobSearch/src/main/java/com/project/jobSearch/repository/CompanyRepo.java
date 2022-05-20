package com.project.jobSearch.repository;

import com.project.jobSearch.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
    @Transactional
    @Query(value = "select distinct * from company join company_jobs on company.id=company_jobs.company_id join job on job.id=company_jobs.job_id where company.location like %:keyword% or job.skill like %:keyword%  ", nativeQuery = true)
    List<Company> findByKeyword(@Param("keyword") String keyword);
    @Transactional
    List<Company> findByLocation(String location);


}
