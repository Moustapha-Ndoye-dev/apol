package com.spring.apol.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class CahierDeCharge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texte;

    @Column(nullable = false)
    private LocalDate date;

    // Relations
    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = false)
    private Utilisateur professeur;

    @ManyToOne
    @JoinColumn(name = "chef_classe_id", nullable = false)
    private Utilisateur chefClasse;
}
