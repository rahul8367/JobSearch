package com.project.jobSearch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "location")
    private String location;
    @Column(name = "company_details")
    private String company_details;
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(name = "company_jobs", joinColumns = { @JoinColumn(name = "company_id") }, inverseJoinColumns = {
            @JoinColumn(name = "job_id") })
    private List<Job> jobList=new ArrayList<>();

}

