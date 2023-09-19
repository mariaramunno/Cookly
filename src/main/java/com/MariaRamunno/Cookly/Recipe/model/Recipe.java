package com.MariaRamunno.Cookly.Recipe.model;

import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import com.MariaRamunno.Cookly.Steps.model.Steps;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private double cooktime;

    @Column(name = "cookbook_id")
    private long cookbook_id;

    @OneToMany
    @JoinColumn(name = "recipe_id")
    private List<Steps> steps;

    private double rate;

    //@ElementCollection
    @OneToMany
    @JoinColumn(name = "recipe_id")
    private Set<Ingredient> ingredients;
}
