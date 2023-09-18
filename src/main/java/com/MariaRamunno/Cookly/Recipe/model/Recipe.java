package com.MariaRamunno.Cookly.Recipe.model;

import com.MariaRamunno.Cookly.Ingredients.model.Ingredient;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private double cooktime;

    @Column(name = "cookbook_id")
    private long cookbookId;

    @OneToMany
    private List<Steps> steps;

    private Integer rate;

    //@ElementCollection
    @OneToMany
    @JoinColumn(name = "recipe_id")
    private Set<Ingredient> ingredients;
}
