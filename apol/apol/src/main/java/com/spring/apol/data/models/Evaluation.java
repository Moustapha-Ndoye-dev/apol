package com.spring.apol.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;


    @Column(nullable = false)
    private String type; // Exemple: Examen, Devoir, etc.

    // Relations
    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private Module module;

    @ManyToMany
    @JoinTable(
            name = "evaluation_classe",
            joinColumns = @JoinColumn(name = "evaluation_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id")
    )
    private List<Classe> classes;

    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteEvaluation> notes;


}
