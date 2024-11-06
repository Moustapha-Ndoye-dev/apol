package com.spring.apol.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.apol.data.enums.RoleUtilisateur;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Le nom complet ne peut pas être vide")
    @Column(nullable = false)
    private String nomComplet;

    @JsonIgnore // Empêche le mot de passe d'être sérialisé dans les réponses JSON
    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Le mois de naissance ne peut pas être vide")
    @Column(nullable = false)
    private String moisNaissance;

    @Column(nullable = false)
    private int anneeNaissance;

    @Column(nullable = false)
    private String telephone;

    @Column()
    private String photo;

    @NotBlank(message = "Le matricule ne peut pas être vide")
    @Column(nullable = false, unique = true)
    private String matricule;

    @NotNull(message = "Le rôle ne peut pas être null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUtilisateur role;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateMiseAJour;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bulletin> bulletins = new ArrayList<>();

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CahierDeCharge> cahiersDeCharge = new ArrayList<>();

    @ManyToMany(mappedBy = "professeurs")
    private List<Module> modules = new ArrayList<>();

    @OneToMany(mappedBy = "chefClasse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CahierDeCharge> cahiersDeChargeChef = new ArrayList<>();

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Syllabus> syllabus = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiereId;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonIgnore // Ignore la classe dans les sérialisations JSON
    private Classe classe; // Référence à la classe de l'utilisateur
}
