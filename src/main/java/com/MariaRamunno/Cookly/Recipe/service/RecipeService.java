package com.MariaRamunno.Cookly.Recipe.service;

import com.MariaRamunno.Cookly.Recipe.model.Recipe;

import java.util.List;

public interface RecipeService {
    Recipe createRecipe(Recipe recipe);
    List<Recipe> getRecipes();
    Recipe getRecipeById(long id);
    Recipe updateRecipe(Recipe recipe);
    void  deleteRecipe(long id);
}
