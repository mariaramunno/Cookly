package com.MariaRamunno.Cookly.Ingredients.exceptions;

public class IngredientNotFoundException extends RuntimeException{
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
