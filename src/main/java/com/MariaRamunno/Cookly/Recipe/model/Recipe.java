package com.MariaRamunno.Cookly.Recipe.model;

import com.MariaRamunno.Cookly.Ingredients.model.Ingredients;
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

    @OneToMany
    private List<Steps> steps;
    private Integer rate;

    @ElementCollection
    private Set<Ingredients> ingredients;
}
