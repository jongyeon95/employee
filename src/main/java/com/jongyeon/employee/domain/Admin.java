package com.jongyeon.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String name;

    private LocalDate createdTime;

    private LocalDate updatedTime;
}
