package com.spring.apol.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class NoteEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double note;

    @Column(length = 500)
    private String commentaires;

    @Column(nullable = false)
    private LocalDateTime dateSubmission;

    // Relations
    @ManyToOne
    @JoinColumn(name = "evaluation_id", nullable = false)
    private Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "bulletin_id") // Nom de la colonne dans la table NoteEvaluation
    private Bulletin bulletin;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Utilisateur etudiant;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = false)
    private Utilisateur professeur;
}
