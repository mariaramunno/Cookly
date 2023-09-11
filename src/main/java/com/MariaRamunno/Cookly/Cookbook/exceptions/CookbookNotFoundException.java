package com.MariaRamunno.Cookly.Cookbook.exceptions;

public class CookbookNotFoundException extends RuntimeException {
    public CookbookNotFoundException(String message) {
        super(message);
    }
}
