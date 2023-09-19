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
@Table(name = "cookbook")
public class Cookbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(name = "user_id")
    private long user_id;

    @OneToMany
    @JoinColumn(name = "cookbook_id")
    private List<Recipe> recipes;
}
