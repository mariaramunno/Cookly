package com.MariaRamunno.Cookly.Ingredients.services;

import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;

import java.util.List;

public interface IngredientsService {


    Ingredient createIngredient(Ingredient ingredient);
    List<Ingredient> getIngredients();

    Ingredient updateIngredient(Ingredient ingredient);

    Ingredient getIngredientById(long id);

    void deleteIngredient(long id);
}
