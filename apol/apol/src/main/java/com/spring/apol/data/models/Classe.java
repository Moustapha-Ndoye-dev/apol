package com.spring.apol.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.apol.data.enums.Niveau;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Niveau niveau;

    @ManyToMany(mappedBy = "classes")
    private List<Module> modules;

    @ManyToMany(mappedBy = "classes")
    private List<Evaluation> evaluations;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}
