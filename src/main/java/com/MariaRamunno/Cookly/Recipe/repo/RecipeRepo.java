package com.MariaRamunno.Cookly.Recipe.repo;

import com.MariaRamunno.Cookly.Recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {
}
