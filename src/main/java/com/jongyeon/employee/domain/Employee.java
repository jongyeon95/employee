package com.jongyeon.employee.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name; //이름

    @NotNull
    private String email; //이메일

    @NotNull
    private Integer position; //직급

    @NotNull
    private LocalDate joinDate; //입사일

    private LocalDate retireDate; //퇴사일

    private Integer vacation; // 남은 연차

    private Integer wages; // 임금

    private String department;//부서

}
