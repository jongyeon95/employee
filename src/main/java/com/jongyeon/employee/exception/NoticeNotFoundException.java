package com.jongyeon.employee.exception;

public class NoticeNotFoundException extends RuntimeException {
    public NoticeNotFoundException(Long id) {
        super("Could not find Notice " + id);
    }
}
