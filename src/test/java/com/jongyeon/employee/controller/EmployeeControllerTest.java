package com.jongyeon.employee.controller;

import com.jongyeon.employee.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Tester")));
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Tester\",\"email\":\"tester@example.com\"}"))
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

}