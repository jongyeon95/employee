package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.exception.EmployeeNotFoundException;
import com.jongyeon.employee.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        employeeService = new EmployeeService(employeeRepository);
    }


    @Test
    public void getEmployeeList() {
        List<Employee> list = new ArrayList<>();
        list.add(Employee.builder().name("Tester").email("tester@example.com").build());
        given(employeeRepository.findAll()).willReturn(list);
        assertThat(employeeService.getEmployeeList(), is(list));
    }

    @Test
    public void getEmployee() {
        Employee employeeMock = Employee.builder()
                .name("Tester")
                .email("tester@naver.com")
                .position(1)
                .joinDate(LocalDate.now())
                .build();
        given(employeeRepository.findById(1L)).willReturn(Optional.ofNullable(employeeMock));
        Employee employee = employeeService.getEmployee(1L);
        assertThat(employee.getName(), is("Tester"));
    }

    @Test
    public void addEmployee() {
        Employee employeeMock = Employee.builder()
                .name("Tester")
                .email("tester@naver.com")
                .position(1)
                .joinDate(LocalDate.now())
                .build();
        employeeService.addEmployee(employeeMock);
        verify(employeeRepository).save(any());
    }

    @Test
    public void updateEmployee() {
        Employee employeeMock = Employee.builder()
                .id(1L)
                .name("Tester")
                .email("tester@naver.com")
                .position(1)
                .joinDate(LocalDate.now())
                .build();
        given(employeeRepository.findById(1L)).willReturn(Optional.of(employeeMock));


        Employee employee = Employee.builder()
                .name("Change")
                .email("tester@naver.com")
                .position(1)
                .joinDate(LocalDate.now())
                .build();
        employeeMock.update(employee);
        given(employeeRepository.save(any())).willReturn(employeeMock);
        Employee changeEmployee = employeeService.updateEmployee(1L, employee);
        verify(employeeRepository).findById(1L);

        assertThat(changeEmployee.getName(), is("Change"));

    }


}