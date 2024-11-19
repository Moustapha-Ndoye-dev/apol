package com.spring.apol.data.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) // Le nom est unique et non null
    private String nom;
}
