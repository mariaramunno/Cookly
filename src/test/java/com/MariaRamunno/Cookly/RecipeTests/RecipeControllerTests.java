package com.MariaRamunno.Cookly.RecipeTests;

import com.MariaRamunno.Cookly.Recipe.controller.RecipeController;
import com.MariaRamunno.Cookly.Recipe.model.Recipe;
import com.MariaRamunno.Cookly.Recipe.service.RecipeService;
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

@WebMvcTest(RecipeController.class)
public class RecipeControllerTests {
    @MockBean
    private RecipeService recipeService;

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
    void createRecipeSuccess() throws Exception{
        Recipe newRecipe= new Recipe();
        Recipe createdRecipe= new Recipe();
        createdRecipe.setId(1L);
        createdRecipe.setTitle("test");
        newRecipe.setTitle("test");
        when(recipeService.createRecipe(any())).thenReturn(createdRecipe);

        mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newRecipe)))
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(createdRecipe)));
    }

    @Test
    void getRecipeByIdSuccess() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        when(recipeService.getRecipeById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(recipe)));
    }

    @Test
    void updateRecipeSuccess() throws Exception {
        Recipe updateRecipe = new Recipe();
        updateRecipe.setId(1L);
        updateRecipe.setTitle("updated");

        Recipe newRecipe = new Recipe();
        newRecipe.setTitle("test");

        when(recipeService.updateRecipe(any())).thenReturn(updateRecipe);

        mockMvc.perform(put("/recipes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newRecipe)))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(updateRecipe)));
    }

    @Test
    void deleteRecipeSuccess() throws Exception {
        mockMvc.perform(delete("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Recipe deleted successfully"));
    }

    @Test
    void getAllRecipes() throws Exception {
        List<Recipe> recipeList = Arrays.asList(new Recipe(), new Recipe());
        when(recipeService.getRecipes()).thenReturn(recipeList);

        mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(recipeList)));
    }
}
