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

    @NotBlank(message = "Le nom complet ne peut pas être vide")
    @Column(name = "nom_complet", nullable = false)
    private String nomComplet;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Le mois de naissance ne peut pas être vide")
    @Column(name = "mois_naissance", nullable = false)
    private String moisNaissance;

    @Column(name = "annee_naissance", nullable = false)
    private int anneeNaissance;

    @Column(name = "jour_de_naissance", nullable = false)
    private int jourDeNaissance;

    @Column(nullable = false)
    private String telephone;

    @Column
    private String photo;

    @NotBlank(message = "Le matricule ne peut pas être vide")
    @Column(nullable = false, unique = true)
    private String matricule;

    @NotNull(message = "Le rôle ne peut pas être null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleUtilisateur role;

    @CreationTimestamp
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    @Column(name = "date_miseajour")
    private LocalDateTime dateMiseAJour;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonIgnore
    private Classe classe;

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
}