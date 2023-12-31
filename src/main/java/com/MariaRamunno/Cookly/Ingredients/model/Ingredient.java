package com.MariaRamunno.Cookly.Ingredients.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "recipe_id")
    private long recipe_id;

    private String name;

    private Integer quantity;

    private String unit;
}
