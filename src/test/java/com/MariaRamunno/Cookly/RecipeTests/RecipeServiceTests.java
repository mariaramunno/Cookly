package com.MariaRamunno.Cookly.RecipeTests;
import com.MariaRamunno.Cookly.Recipe.exceptions.RecipeNotFoundException;
import com.MariaRamunno.Cookly.Recipe.model.Recipe;
import com.MariaRamunno.Cookly.Recipe.repo.RecipeRepo;
import com.MariaRamunno.Cookly.Recipe.service.RecipeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTests {

    @Mock
    private RecipeRepo recipeRepo;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    void getAllRecipes() {
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeRepo.findAll()).thenReturn(recipes);

        List<Recipe> result = recipeService.getRecipes();

        assertEquals(recipes, result);
    }
    @Test
    public void createRecipeSuccess() {
        Recipe recipe = new Recipe();
        when(recipeRepo.save(recipe)).thenReturn(recipe);

        Recipe result = recipeService.createRecipe(recipe);

        assertEquals(recipe, result);
    }

    @Test
    public void updateRecipeSuccess() {
        long id = 1L;
        Recipe existingRecipe = new Recipe();
        existingRecipe.setId(id);
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(id);
        when(recipeRepo.findById(id)).thenReturn(Optional.of(existingRecipe));
        when(recipeRepo.save(existingRecipe)).thenReturn(updatedRecipe);

        Recipe result = recipeService.updateRecipe(updatedRecipe);

        assertEquals(updatedRecipe, result);
    }

    @Test
    void updateRecipeNotFound() {
        long id = 1L;
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(id);
        when(recipeRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.updateRecipe(updatedRecipe);
        });
    }
    @Test
    public void getRecipeByIdSuccess() {
        Recipe recipe = new Recipe();
        long id = 1L;
        when(recipeRepo.findById(id)).thenReturn(Optional.of(recipe));

        Recipe result = recipeService.getRecipeById(id);

        assertEquals(recipe, result);
    }

    @Test
    void getRecipeByIdNotFound() {
        long id = 1L;
        when(recipeRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecipeNotFoundException.class, () -> {
            recipeService.getRecipeById(id);
        });
    }
}
