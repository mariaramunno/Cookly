package com.MariaRamunno.Cookly.User.model;

import com.MariaRamunno.Cookly.Cookbook.model.Cookbook;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private Set<Cookbook> cookbook;
}
