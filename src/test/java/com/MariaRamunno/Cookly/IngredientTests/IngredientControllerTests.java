package com.MariaRamunno.Cookly.IngredientTests;

import com.MariaRamunno.Cookly.Ingredients.controller.IngredientController;
import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import com.MariaRamunno.Cookly.Ingredients.services.IngredientsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IngredientController.class)
public class IngredientControllerTests {


    @MockBean
    private IngredientsService ingredientsService;

    @Autowired
    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createIngredientSuccess() throws Exception{
        Ingredient newIngredient = new Ingredient();
        Ingredient createdIngredient = new Ingredient();
        createdIngredient.setId(1L);
        when(ingredientsService.createIngredient(any())).thenReturn(createdIngredient);

        mockMvc.perform(post("/ingredients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newIngredient)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(createdIngredient)));
    }

    @Test
    void getIngredientByIdSuccess() throws Exception{
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);
        when(ingredientsService.getIngredientById(1L)).thenReturn(ingredient);

        mockMvc.perform(get("/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(ingredient)));
    }

    @Test
    void updateIngredientSuccess() throws Exception {
        Ingredient updateIngredient = new Ingredient();
        updateIngredient.setId(1L);
        updateIngredient.setName("updated");

        Ingredient newIngredient = new Ingredient();
        newIngredient.setName("test");

        when(ingredientsService.updateIngredient(any())).thenReturn(updateIngredient);

        mockMvc.perform(put("/ingredients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newIngredient)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updateIngredient)));
    }

    @Test
    void deleteIngredientSuccess() throws Exception {

        mockMvc.perform(delete("/ingredients/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Ingredient deleted successfully"));
    }

    @Test
    void getAllIngredients() throws Exception {
        List<Ingredient> ingredientsList = Arrays.asList(new Ingredient(), new Ingredient());
        when(ingredientsService.getIngredients()).thenReturn(ingredientsList);

        mockMvc.perform(get("/ingredients"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(ingredientsList)));
    }

}
