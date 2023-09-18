package com.MariaRamunno.Cookly.IngredientTests;

import com.MariaRamunno.Cookly.Ingredients.exceptions.IngredientNotFoundException;
import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import com.MariaRamunno.Cookly.Ingredients.repo.IngredientRepo;
import com.MariaRamunno.Cookly.Ingredients.services.IngredientServiceImpl;
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
public class IngredientServiceTests {

    @Mock
    private IngredientRepo ingredientRepo;

    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Test
    void getAllIngredients() {
        List<Ingredient> ingredients = Arrays.asList(new Ingredient(), new Ingredient());
        when(ingredientRepo.findAll()).thenReturn(ingredients);

        List<Ingredient> result = ingredientService.getIngredients();

        assertEquals(ingredients, result);
    }
    @Test
    public void createIngredientSuccess() {
        Ingredient ingredient = new Ingredient();
        when(ingredientRepo.save(ingredient)).thenReturn(ingredient);

        Ingredient result = ingredientService.createIngredient(ingredient);

        assertEquals(ingredient, result);
    }

    @Test
    public void updateIngredientSuccess() {
        long id = 1L;
        Ingredient existingIngredient = new Ingredient();
        existingIngredient.setId(id);
        Ingredient updatedIngredient = new Ingredient();
        updatedIngredient.setId(id);
        when(ingredientRepo.findById(id)).thenReturn(Optional.of(existingIngredient));
        when(ingredientRepo.save(existingIngredient)).thenReturn(updatedIngredient);

        Ingredient result = ingredientService.updateIngredient(updatedIngredient);

        assertEquals(updatedIngredient, result);
    }

    @Test
    void updateIngredientNotFound() {
        long id = 1L;
        Ingredient updatedIngredient = new Ingredient();
        updatedIngredient.setId(id);
        when(ingredientRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(IngredientNotFoundException.class, () -> {
            ingredientService.updateIngredient(updatedIngredient);
        });
    }
    @Test
    public void getIngredientByIdSuccess() {
        Ingredient ingredient = new Ingredient();
        long id = 1L;
        when(ingredientRepo.findById(id)).thenReturn(Optional.of(ingredient));

        Ingredient result = ingredientService.getIngredientById(id);

        assertEquals(ingredient, result);
    }

    @Test
    void getIngredientByIdNotFound() {
        long id = 1L;
        when(ingredientRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(IngredientNotFoundException.class, () -> {
            ingredientService.getIngredientById(id);
        });
    }

}
