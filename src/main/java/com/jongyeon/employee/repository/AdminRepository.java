package com.jongyeon.employee.repository;


import com.jongyeon.employee.domain.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
}
