package com.jongyeon.employee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacation {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long employeeId;

    private Integer status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate start;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate end;

    public boolean checkValid(){
        if(!start.isBefore(end)){
            this.status=-1;
            return false;
        }
        return true;
    }
}
