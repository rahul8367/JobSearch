package com.project.jobSearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name = "job_title")
    private String job_title;
    @Column(name = "job_description")
    private String job_description;
    @Column(name="skill")
    private String skill;
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "jobList")
    @JsonIgnore
    private List<Company> listOfCompany =new ArrayList<>();

    public void setListOfCompany(Company listOfCompany) {
        this.listOfCompany.add(listOfCompany);
    }
}
