package com.MariaRamunno.Cookly.Ingredients.repo;

import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, Long> {
}
