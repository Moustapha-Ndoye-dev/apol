package com.spring.apol.data.repositories;

import com.spring.apol.data.models.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {
    // Méthode pour trouver tous les emplois du temps d'une classe donnée
    List<EmploiDuTemps> findByClasseId(Long classeId);

    // Méthode pour trouver tous les emplois du temps d'une filière donnée
    List<EmploiDuTemps> findByFiliereId(Long filiereId);
}
