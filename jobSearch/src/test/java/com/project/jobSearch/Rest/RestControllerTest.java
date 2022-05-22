package com.project.jobSearch.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.jobSearch.Service.CompanyServiceImp;
import com.project.jobSearch.entity.Company;
import com.project.jobSearch.entity.Job;
import com.project.jobSearch.repository.CompanyRepo;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.junit.Assert.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

@RunWith(SpringJUnit4ClassRunner.class)

public class RestControllerTest {
    @Autowired
    private MockMvc mocMvc;
    @MockBean
    private CompanyRepo restController;
    private CompanyServiceImp companyService;
    @org.junit.Test
    public void createAndSaveData() throws Exception {
        Company mocCompany=new Company();
        Job mocJob=new Job();
        mocCompany.setId(13);
        mocCompany.setCompany_name("zemoso");
        mocCompany.setLocation("Hyderabad");
        mocCompany.setCompany_details("the zemoso is the one of the best company for learners");
        mocJob.setId(13);
        mocJob.setJob_title("full stack");
        mocJob.setJob_description("the role is deals with the front end and back end");
        mocJob.setSkill("java");
        List<Job> addValue=new ArrayList<>();
        addValue.add(mocJob);
        mocCompany.setJobList(addValue);
        String inputJ=this.mapToJson(mocCompany);
        String URI="/api/save";
        System.out.println(inputJ);
        Mockito.when(companyService.add(Mockito.any(Company.class))).thenReturn(mocCompany);
        RequestBuilder requst= MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputJ).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mocMvc.perform(requst).andReturn();
        MockHttpServletResponse response=result.getResponse();
        String outJson=response.getContentAsString();
        Assertions.assertEquals(inputJ,outJson);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }
    @Test
    public void readAllTest()throws Exception{
        List<Company> xList=restController.findAll();
        assertNotNull(xList);
    }

    private String mapToJson(Object object)throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}