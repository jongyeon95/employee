package com.jongyeon.employee.service;

import com.jongyeon.employee.domain.Employee;
import com.jongyeon.employee.domain.Vacation;
import com.jongyeon.employee.exception.EmployeeNotFoundException;
import com.jongyeon.employee.exception.VacationNotFoundException;
import com.jongyeon.employee.repository.EmployeeRepository;
import com.jongyeon.employee.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacationService {

    private VacationRepository vacationRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public VacationService(VacationRepository vacationRepository
            , EmployeeRepository employeeRepository) {
        this.vacationRepository = vacationRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Vacation> getVacations() {
        return vacationRepository.findAll();
    }

    public Vacation getVacation(Long id) {
        return vacationRepository.findById(id).orElseThrow(() -> new VacationNotFoundException(id));
    }

    public Vacation addVacation(Vacation resource) {
        Employee employee= employeeRepository.findById(resource.getEmployeeId())
                .orElseThrow(()->new EmployeeNotFoundException(resource.getEmployeeId()));

        if(resource.checkValid()){
            Period period=Period.between(resource.getStart(),resource.getEnd());
            if(period.getDays()>employee.getVacation())
                resource.setStatus(-1);
            else
                resource.setStatus(0);
        }
        return  vacationRepository.save(resource);
    }

    public Vacation updateVacation(Long id, Vacation resource) {

        Vacation vacation= vacationRepository.findById(id).orElseThrow(()->new VacationNotFoundException(id));

        if(resource.getStart()!=null){
            vacation.setStart(resource.getStart());
        }
        if(resource.getEnd()!=null){
            vacation.setEnd(resource.getEnd());
        }
        if(resource.getStatus()!=null){
            vacation.setStatus(resource.getStatus());
        }

        vacationRepository.save(vacation);

        return  vacation;
    }

    public  List<Vacation> getVacationsWhenDate(LocalDate start, LocalDate end){
        List<Vacation> list=vacationRepository.findAllByStartGreaterThanEqualAndStartLessThanEqual(start,end);
        return list;
    }
}
