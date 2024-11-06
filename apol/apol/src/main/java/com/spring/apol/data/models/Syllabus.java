package com.spring.apol.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String objectifs;

    @Column(nullable = false)
    private LocalDate dateCreation;

    @Column(nullable = false)
    private int dureeEnSemaines;

    @Column
    private String cheminDocument;

    // Relations
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = false)
    private Utilisateur professeur;
}
