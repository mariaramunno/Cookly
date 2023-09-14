package com.MariaRamunno.Cookly.Cookbook.model;

import com.MariaRamunno.Cookly.Recipe.model.Recipe;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cookbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private List<Recipe> recipes;
}
