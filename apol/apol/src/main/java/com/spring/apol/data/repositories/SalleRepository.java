package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalleRepository extends JpaRepository<Salle, Long> {
    // Ajoute des méthodes personnalisées si nécessaire
    Optional<Salle> findByNom(String nom);
}
