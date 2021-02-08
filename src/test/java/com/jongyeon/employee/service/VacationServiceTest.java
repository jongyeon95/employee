package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.domain.Vacation;
import com.jongyeon.employee.repository.EmployeeRepository;
import com.jongyeon.employee.repository.VacationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class VacationServiceTest {

    private VacationService vacationService;

    @Mock
    private VacationRepository vacationRepository;
    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        vacationService=new VacationService(vacationRepository,employeeRepository);
    }


    @Test
    public void getVacations(){
        List<Vacation> list = new ArrayList<>();
        list.add(Vacation.builder()
                .id(1L)
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,30))
                .build());
        given(vacationRepository.findAll()).willReturn(list);
        assertThat(vacationService.getVacations().get(0).getId(),is(1L));
    }

    @Test
    public void addVacations(){
        Vacation vacation= Vacation.builder()
                .employeeId(1L)
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,27))
                .build();
        Employee employee= Employee.builder()
                .id(1L)
                .name("Tester")
                .vacation(3)
                .build();
        given(employeeRepository.findById(any())).willReturn(Optional.of(employee));
        vacationService.addVacation(vacation);
        verify(vacationRepository).save(any());
    }

    @Test
    public void updateVacations(){
        Vacation mockVacation= Vacation.builder()
                .id(1L)
                .employeeId(1L)
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,27))
                .build();

        Vacation changeVacation= Vacation.builder()
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,29))
                .build();

        given(vacationRepository.findById(1L)).willReturn(Optional.of(mockVacation));

        Vacation vacation=vacationService.updateVacation(1L,changeVacation);
        assertThat(vacation.getEnd(),is(LocalDate.of(2021,1,29)));
        verify(vacationRepository).save(any());
    }

    @Test
    public void getVacationsWhenDate(){
        LocalDate start=LocalDate.now();
        LocalDate end=start.plusDays(3);
        List<Vacation> mockList = new ArrayList<>();
        for(int i=1; i<=3; i++){
            mockList.add(Vacation.builder()
                    .id(Long.valueOf(i))
                    .start(start)
                    .end(start.plusDays(i))
                    .build());
        }
        given(vacationRepository.findAllByStartGreaterThanEqualAndStartLessThanEqual(start,end)).willReturn(mockList);
        List<Vacation> list = vacationService.getVacationsWhenDate(start,end);
        assertThat(list.size(),is(3));

    }



}