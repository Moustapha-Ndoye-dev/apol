package com.spring.apol.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column
    private String description;

//    @Column(nullable = false)
//    private Double coefficient;
    // Relations
    @ManyToMany
    @JoinTable(
            name = "module_prof",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "professeur_id")
    )
    private List<Utilisateur> professeurs;

    @ManyToMany
    @JoinTable(
            name = "module_classe",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "classe_id")
    )
    private List<Classe> classes;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Syllabus> syllabus;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations;
}
