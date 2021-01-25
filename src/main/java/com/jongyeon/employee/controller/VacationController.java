package com.jongyeon.employee.controller;

import com.jongyeon.employee.domain.Vacation;
import com.jongyeon.employee.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VacationController {

    @Autowired
    VacationService vacationService;

    @GetMapping("/vacations")
    public List<Vacation> list(){
        return vacationService.getVacations();
    }

    @GetMapping("/vacation/{id}")
    public Vacation getVacation(@PathVariable("id") Long id){
        Vacation vacation=vacationService.getVacation(id);
        return vacation;
    }

    @PostMapping("/vacation")
    public ResponseEntity<?> create(@RequestBody @Valid Vacation resource) throws URISyntaxException {
        Vacation vacation=vacationService.addVacation(resource);
        URI location=new URI( "/vacation/"+vacation.getId());
        return ResponseEntity.created(location).body("{}");
    }

    @PatchMapping("/vacation/{id}")
    public Vacation updateVacation(@RequestBody Vacation resource,
            @PathVariable("id") Long id){
        Vacation vacation=vacationService.updateVacation(id,resource);
        return vacation;
    }

}
