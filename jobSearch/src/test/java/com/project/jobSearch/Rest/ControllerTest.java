package com.project.jobSearch.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.jobSearch.Service.CompanyServiceImp;
import com.project.jobSearch.entity.Company;
import com.project.jobSearch.entity.Job;
import com.project.jobSearch.repository.CompanyRepo;
import com.project.jobSearch.repository.JobRepo;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@RunWith(SpringJUnit4ClassRunner.class)

public class ControllerTest {
    @Autowired
    private MockMvc mocMvc;
    @MockBean
    private CompanyRepo restController;
    @MockBean
    private JobRepo jobRepo;
    private CompanyServiceImp companyService;
    @org.junit.Test
    public void createAndSaveData() throws Exception {
        Company mocCompany=new Company();
        Job mocJob=new Job();
        mocCompany.setId(15);
        mocCompany.setCompany_name("zemoso");
        mocCompany.setLocation("Hyderabad");
        mocCompany.setCompany_details("the zemoso is the one of the best company for learners");
        mocJob.setId(15);
        mocJob.setJob_title("full stack");
        mocJob.setJob_description("the role is deals with the front end and back end");
        mocJob.setSkill("java");
        List<Job> addValue=new ArrayList<>();
        addValue.add(mocJob);
        mocCompany.setJobList(addValue);
        restController.save(mocCompany);
        verify(restController, times(1)).save(mocCompany);
    }
    @Test
    public void findByIdTest() {
        long id=15L;
        Assertions.assertNotNull(restController.findById(id));
    }
    @Test
    public void readAllTest()throws Exception{
        List<Company> xList=restController.findAll();
        assertNotNull(xList);
    }
    @Test
    public void readAllJobs() throws Exception{
        List<Job> yList=jobRepo.findAll();
        assertNotNull(yList);
    }
    @Test
    public void deleteProductTest() {
        long id = 10L;
        restController.deleteById(id);
        verify(restController, times(1)).deleteById(id);
    }
    private String mapToJson(Object object)throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}