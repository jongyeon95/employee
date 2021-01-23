package com.jongyeon.employee.repository;

import com.jongyeon.employee.domain.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    String name="Tester";
    String email="tester@example.com";
    Integer position=3;
    LocalDate joinDate= LocalDate.now();
    LocalDate retireDate=LocalDate.MAX;
    Integer vacation=3;
    Integer wages=40000000;
    String department="Develop";
    
    @Test
    public void save(){

        Employee mockEmployee=Employee.builder()
                .name(name).email(email)
                .position(position)
                .joinDate(joinDate)
                .retireDate(retireDate)
                .vacation(vacation)
                .wages(wages)
                .department(department)
                .build();
        Employee employee=employeeRepository.save(mockEmployee);
        assertThat(employee.getEmail(),is(email));

    }

    @Test
    public void findByName(){

        Employee mockEmployee=Employee.builder()
                .name(name).email(email)
                .position(position)
                .joinDate(joinDate)
                .retireDate(retireDate)
                .vacation(vacation)
                .wages(wages)
                .department(department)
                .build();
        Employee employee=employeeRepository.save(mockEmployee);
        Employee findEmployee=employeeRepository.findByName(name).orElse(null);
        assertThat(findEmployee.getName(),is(name));

    }
}