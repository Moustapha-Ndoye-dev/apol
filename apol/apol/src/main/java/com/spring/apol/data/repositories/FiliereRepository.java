package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    // Méthode pour trouver une filière par son nom, si nécessaire
    Optional<Filiere> findByNom(String nom);
}
