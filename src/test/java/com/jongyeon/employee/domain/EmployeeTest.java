package com.jongyeon.employee.domain;


import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmployeeTest {

    @Test
    public void create() {
        Employee employee = Employee.builder()
                .name("Tester")
                .email("tester@example.com")
                .position(3)
                .joinDate(LocalDate.now())
                .build();
        assertThat(employee.getName(), is("Tester"));
        assertThat(employee.getEmail(), is("tester@example.com"));
        assertThat(employee.getPosition(), is(3));
        assertThat(employee.getJoinDate(), is(LocalDate.now()));
    }
}
