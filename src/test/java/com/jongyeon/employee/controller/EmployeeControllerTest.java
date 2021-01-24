package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.repository.EmployeeRepository;
import com.jongyeon.employee.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    EmployeeService employeeService;


    @Test
    public void list() throws Exception {
        List<Employee> list = new ArrayList<>();
        list.add(Employee.builder().name("Tester").build());
        given(employeeService.getEmployeeList()).willReturn(list);

        mvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tester")));
    }

    @Test
    public void create() throws Exception {

        mvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tester\",\"email\":\"tester@example.com\"" +
                        ",\"position\":1,\"joinDate\":\"2021-01-20\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

    }

    @Test
    public void createWithInvalid() throws Exception {
        mvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\",\"email\":\"\"}"))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void update() throws Exception {

        mvc.perform(patch("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"change\",\"email\":\"change@example.com\"}"))
                .andExpect(status().isOk());

        verify(employeeService).updateEmployee(any(), any());
    }

}