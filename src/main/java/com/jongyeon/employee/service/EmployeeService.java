package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.exception.EmployeeNotFoundException;
import com.jongyeon.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Long id){
        Employee employee =employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException(id));
        return employee;
    }

    public Employee addEmployee(Employee e){
        return employeeRepository.save(e);
    }



}
