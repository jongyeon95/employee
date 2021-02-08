package com.jongyeon.employee.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name; //이름

    @NotEmpty
    private String email; //이메일

    @NotNull
    private Integer position; //직급

    @NotNull
    private LocalDate joinDate; //입사일

    private LocalDate retireDate; //퇴사일

    private Integer vacation; // 남은 연차

    private Integer wages; // 임금

    private String department;//부서



    public void update(Employee e){
        if(e.getName()!=null){
            this.name=e.getName();
        }
        if(e.getEmail()!=null){
            this.email=e.getEmail();
        }
        if(e.getPosition()!=null){
            this.position=e.getPosition();
        }
        if(e.getJoinDate()!=null){
            this.joinDate=e.getJoinDate();
        }
        if(e.getRetireDate()!=null){
            this.retireDate=e.getRetireDate();
        }
        if(e.getVacation()!=null){
            this.vacation=e.getVacation();
        }
        if(e.getWages()!=null){
            this.wages=e.getWages();
        }
        if(e.getDepartment()!=null){
            this.department=e.getDepartment();
        }
    }

}
