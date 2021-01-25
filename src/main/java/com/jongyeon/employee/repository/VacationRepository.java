package com.jongyeon.employee.repository;

import com.jongyeon.employee.domain.Vacation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
    List<Vacation> findAll();
    Vacation save(Vacation v);
    Optional<Vacation> findById(Long id);
}
