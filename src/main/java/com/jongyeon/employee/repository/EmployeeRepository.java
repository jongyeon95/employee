package com.jongyeon.employee.repository;

import com.jongyeon.employee.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee , Long> {

    Employee save(Employee e);
    Optional<Employee> findById(Long id);

}
