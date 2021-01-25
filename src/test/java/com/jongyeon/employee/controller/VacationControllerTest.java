package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.domain.Vacation;
import com.jongyeon.employee.repository.EmployeeRepository;
import com.jongyeon.employee.repository.VacationRepository;
import com.jongyeon.employee.service.VacationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
@WebMvcTest(VacationController.class)
public class VacationControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    VacationService vacationService;

    @Mock
    VacationRepository vacationRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void list() throws Exception {
        List<Vacation> list = new ArrayList<>();
        list.add(Vacation.builder()
                .id(1L)
                .start(LocalDate.of(2021, 1, 25))
                .end(LocalDate.of(2021, 1, 30))
                .build());
        given(vacationService.getVacations()).willReturn(list);

        mvc.perform(get("/vacations"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2021-01-25")));
    }

    @Test
    public void create() throws Exception {
        Vacation vacation = Vacation.builder()
                .employeeId(1L)
                .start(LocalDate.of(2021, 1, 25))
                .end(LocalDate.of(2021, 1, 27))
                .build();
        Employee employee = Employee.builder()
                .id(1L)
                .vacation(3)
                .build();

        given(employeeRepository.findById(any())).willReturn(Optional.of(employee));
        given(vacationService.addVacation(vacation)).willReturn(vacation);

        mvc.perform(post("/vacation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"2021-01-25\",\"end\":\"2021-01-27\",\"employeeId\":1}"))
                .andExpect(status().isCreated());

    }

    @Test
    public void createInvalidDate() throws Exception {
        Vacation vacation = Vacation.builder()
                .employeeId(1L)
                .start(LocalDate.of(2021, 1, 25))
                .end(LocalDate.of(2021, 1, 27))
                .build();
        Employee employee = Employee.builder()
                .id(1L)
                .vacation(3)
                .build();

        given(employeeRepository.findById(any())).willReturn(Optional.of(employee));
        given(vacationService.addVacation(any())).willReturn(vacation);

        mvc.perform(post("/vacation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"\",\"end\":\"\",\"employeeId\":1}"))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void update() throws Exception {
        Vacation mockVacation = Vacation.builder()
                .start(LocalDate.of(2021, 1, 25))
                .end(LocalDate.of(2021, 1, 29))
                .build();

        mvc.perform(patch("/vacation/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"start\":\"2021-01-25\",\"end\":\"2021-01-29\"}"))
                .andExpect(status().isOk());

        verify(vacationService).updateVacation(1L,mockVacation);

    }


}