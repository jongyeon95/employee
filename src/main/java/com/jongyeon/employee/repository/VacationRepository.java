package com.jongyeon.employee.repository;

import com.jongyeon.employee.domain.Vacation;
import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
    List<Vacation> findAll();
    Vacation save(Vacation v);
    Optional<Vacation> findById(Long id);
    List<Vacation> findAllByStartGreaterThanEqualAndStartLessThanEqual(LocalDate start, LocalDate end);
}
