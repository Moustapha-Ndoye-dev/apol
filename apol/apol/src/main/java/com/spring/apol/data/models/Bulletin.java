package com.spring.apol.data.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomEtablissement;
    private String adresseEtablissement;
    private String emailEtablissement;
    private String telephoneEtablissement;

    private String appreciation;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Utilisateur etudiant;

    @OneToMany(mappedBy = "bulletin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteEvaluation> notes;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;
}
