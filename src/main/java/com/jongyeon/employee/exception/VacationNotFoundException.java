package com.jongyeon.employee.exception;

public class VacationNotFoundException extends RuntimeException {
    public VacationNotFoundException(Long id) {
        super("Could not find Vacation " + id);

    }
}
