package com.MariaRamunno.Cookly.Ingredients.controller;

import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import com.MariaRamunno.Cookly.Ingredients.services.IngredientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    @Autowired
    private IngredientsService ingredientsService;

    @GetMapping
    public List<Ingredient> getIngredients(){
        return ingredientsService.getIngredients();
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient){
        Ingredient createdIngredient = ingredientsService.createIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdIngredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
        Ingredient updatedIngredient = ingredientsService.updateIngredient(ingredient);
        return ResponseEntity.ok(updatedIngredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable long id){
        ingredientsService.deleteIngredient(id);
        return ResponseEntity.ok("Ingredient deleted successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id){
        Ingredient ingredient = ingredientsService.getIngredientById(id);
        return ResponseEntity.ok(ingredient);
    }
}
