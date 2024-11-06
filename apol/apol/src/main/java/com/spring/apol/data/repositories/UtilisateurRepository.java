package com.spring.apol.data.repositories;

import com.spring.apol.data.enums.RoleUtilisateur;
import com.spring.apol.data.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Recherche un utilisateur par son email
    Optional<Utilisateur> findByEmail(String email);

    // Recherche un utilisateur par son matricule
    Optional<Utilisateur> findByMatricule(String matricule);

    // Recherche tous les utilisateurs par rôle
    List<Utilisateur> findByRole(RoleUtilisateur role);

    // Recherche tous les utilisateurs dont le nom complet contient une chaîne donnée
    List<Utilisateur> findByNomCompletContaining(String nomComplet);

    // Vérifie si un utilisateur éxiste par son email
    boolean existsByEmail(String email);

    // Vérifie si un utilisateur éxiste par son matricule
    boolean existsByMatricule(String matricule);

    // Supprime un utilisateur par son ID
    void deleteById(Long id);
}
