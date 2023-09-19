package com.MariaRamunno.Cookly.Steps.exceptions;

public class StepNotFoundException extends RuntimeException {
    public StepNotFoundException(String message) {
        super(message);
    }
}
