package com.MariaRamunno.Cookly.Recipe.service;

import com.MariaRamunno.Cookly.Recipe.exceptions.RecipeNotFoundException;
import com.MariaRamunno.Cookly.Recipe.model.Recipe;
import com.MariaRamunno.Cookly.Recipe.repo.RecipeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepo recipeRepo;
    @Override
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepo.save(recipe);
    }

    @Override
    public List<Recipe> getRecipes() {
        return recipeRepo.findAll();
    }

    @Override
    public Recipe getRecipeById(long id) {
        return recipeRepo.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("No recipe found with this id:" + id));
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        Recipe newRecipe = recipeRepo.findById(recipe.getId())
                .orElseThrow(() -> new RecipeNotFoundException("Sorry, this recipe could not be found."));

        newRecipe.setCooktime(recipe.getCooktime());
        newRecipe.setIngredients(recipe.getIngredients());
        newRecipe.setRate(recipe.getRate());
        newRecipe.setSteps(recipe.getSteps());
        newRecipe.setTitle(recipe.getTitle());

        return recipeRepo.save(newRecipe);
    }

    @Override
    public void deleteRecipe(long id) {
        if(!recipeRepo.existsById(id)){
            throw new RecipeNotFoundException("Sorry, no recipe found.");
        }
        recipeRepo.deleteById(id);
    }
}
