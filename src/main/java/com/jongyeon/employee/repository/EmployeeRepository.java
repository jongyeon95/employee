package com.jongyeon.employee.repository;

import com.jongyeon.employee.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee , Long> {

    Employee save(Employee e);
    Optional<Employee> findByName(String name);
    Optional<Employee> findById(Long id);
    List<Employee> findAll();
    List<Employee> findAllByDepartment(String name);

}
