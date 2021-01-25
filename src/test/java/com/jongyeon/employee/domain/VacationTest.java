package com.jongyeon.employee.domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class VacationTest {

    @Test
    public void create(){
        Vacation vacation= Vacation.builder()
                .employeeId(1L)
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,26))
                .build();
        assertThat(vacation.getEmployeeId(),is(1L));
    }

    @Test
    public void checkValidDate(){
        Vacation vacation= Vacation.builder()
                .employeeId(1L)
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,26))
                .build();
        assertThat(vacation.checkValid(),is(true));

    }

    @Test
    public void checkInvalidDate(){
        Vacation vacation= Vacation.builder()
                .employeeId(1L)
                .start(LocalDate.of(2021,1,25))
                .end(LocalDate.of(2021,1,24))
                .build();
        assertThat(vacation.checkValid(),is(false));
    }


}