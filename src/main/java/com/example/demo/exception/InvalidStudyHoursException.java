package com.example.demo.exception;

public class InvalidStudyHoursException extends RuntimeException {

    public InvalidStudyHoursException(String message) {
        super(message);
    }
}
