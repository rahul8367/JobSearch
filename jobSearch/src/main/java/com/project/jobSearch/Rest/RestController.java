package com.project.jobSearch.Rest;

import com.project.jobSearch.repository.CompanyRepo;
import com.project.jobSearch.repository.JobRepo;
import com.project.jobSearch.entity.Company;
import com.project.jobSearch.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
@RequestMapping("/api")
public class RestController {

    @Autowired
    private CompanyRepo companyRepository;
    @Autowired
    private JobRepo jobRepository;

    //searching the location or skill..................................................................
    @Transactional
    @GetMapping("/search/{value}")
    public List<Company> findByKeyword(@PathVariable String value) {
        return companyRepository.findByKeyword(value);
    }

    //searching by location...........................................................................
    @Transactional
    @GetMapping("/search/location/{value}")
    public List<Company> findByLocation(@PathVariable String value) {
        return companyRepository.findByLocation(value);
    }

    //Listing the all the company's....................................................................
    @Transactional
    @GetMapping("/company/list")
    public List<Company> getCompany() {
        return companyRepository.findAll();
    }
    //add the company................................................................................
    @Transactional
    @PostMapping("/save")
    public Company saveData(@RequestBody Company req) {
        return companyRepository.save(req);
    }

    //delete the company.................................................................................
    @DeleteMapping("/company/delete/{id}")
    public String getSalariesById(@PathVariable("id") long id) {
        try {
            Company Obj = companyRepository.getById(id);
        if (Obj != null) {
            companyRepository.deleteById(id);
            return "the record is deleted";
        }
        }catch (Exception e){
            return "the record  is not there ";
        }
        return "no error but not found id";

    }
    //update the company details...............................................................................

    @PutMapping("/company/update/{id}")
    public String updateCompany(@PathVariable("id") long id, @RequestBody Company company) {

        //check if employee exist in database
     try{   Company Obj = companyRepository.getById(id);

        if (Obj != null) {
            Obj.setCompany_name(company.getCompany_name());
            Obj.setCompany_details(company.getCompany_details());
            Obj.setLocation(company.getLocation());
            Obj.setJobList(company.getJobList());
            companyRepository.save(Obj);
            return "updated";
        }
     }catch (Exception e){
        return "the record  is not there ";
    }
        return "no error but not found id";


    }
// deleting all the company's........................................................................................
    @DeleteMapping("/company")
    public String deleteAllEmployees() {
        try {
            companyRepository.deleteAll();
            return "deleted all";
        } catch (Exception e){
        return "the record  is not there ";
    }

    }
    // the job is removed from the company.........................................................................
    @DeleteMapping("/job/{id}/{jobTitle}")
     public String deleteTheJobFromCompanyByCompanyId(@PathVariable("id") Long id,@PathVariable String jobTitle){
        try{
            Company Obj = companyRepository.getById(id);
            for(Job s:Obj.getJobList()){
                if(s.getJob_title().equalsIgnoreCase(jobTitle)) {
                    Obj.getJobList().remove(s);
                    return "the element is removed";
                }
            }
            companyRepository.save(Obj);
        }catch (Exception e){
        }
        return "the element is not present";
     }
     // listing of jobs...........................................................................................
    @GetMapping("/job/list")
    public List<Job> getJob(){
        return jobRepository.findAll();
    }
    //adding the job to the company
    @PutMapping("/job/addJob/{id}")
    public Company addJob(@PathVariable("id") Long id,@RequestBody Job job){

            Company Obj = companyRepository.getById(id);
                Obj.getJobList().add(job);
                return companyRepository.save(Obj);
            }


}


