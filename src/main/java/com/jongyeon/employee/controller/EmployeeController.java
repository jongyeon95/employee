package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public List<Employee> list(){
       return employeeService.getEmployeeList();
    }

    @GetMapping("/employee/department/{name}")
    public List<Employee> departmentPeopleList(@PathVariable("name") String name){
        return employeeService.getDepartmentPeopleList(name);
    }

    @GetMapping("/employee/{id}")
    public Employee detail(@PathVariable("id") Long id){
        Employee employee= employeeService.getEmployee(id);
        return employee;
    }

    @PostMapping("/employee")
    public ResponseEntity<?> create( @RequestBody @Valid Employee resource) throws URISyntaxException {
        Employee employee = Employee.builder()
                .name(resource.getName())
                .email(resource.getEmail())
                .joinDate(resource.getJoinDate())
                .vacation(0)
                .position(1)
                .build();
        employeeService.addEmployee(employee);
        URI location = new URI("/employee/"+employee.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/employee/{id}")
    public Employee update(@PathVariable("id") Long id, @RequestBody Employee resource){
       return employeeService.updateEmployee(id,resource);
    }
}
