package com.MariaRamunno.Cookly.Ingredients.services;

import com.MariaRamunno.Cookly.Ingredients.exceptions.IngredientNotFoundException;
import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import com.MariaRamunno.Cookly.Ingredients.repo.IngredientRepo;
import com.MariaRamunno.Cookly.Recipe.exceptions.RecipeNotFoundException;
import com.MariaRamunno.Cookly.Recipe.model.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientsService{

    private final IngredientRepo ingredientRepo;
    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }

    @Override
    public List<Ingredient> getIngredients() {
        return ingredientRepo.findAll();
    }

    @Override
    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient newIngredient = ingredientRepo.findById(ingredient.getId())
                .orElseThrow(() -> new IngredientNotFoundException("Sorry, this ingredient could not be found."));

        newIngredient.setTitle(ingredient.getTitle());

        return ingredientRepo.save(newIngredient);
    }

    @Override
    public Ingredient getIngredientById(long id) {
        return ingredientRepo.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("No ingredient found with this id:" + id));
    }

    @Override
    public void deleteIngredient(long id) {
        if(!ingredientRepo.existsById(id)){
            throw new IngredientNotFoundException("Sorry, no ingredient found.");
        }
        ingredientRepo.deleteById(id);
    }
}
