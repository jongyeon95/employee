package com.jongyeon.employee.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id){
        super("Could not find Employee "+id);

    }
}
