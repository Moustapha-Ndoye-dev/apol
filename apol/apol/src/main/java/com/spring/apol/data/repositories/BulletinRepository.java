package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {
    // Trouver tous les bulletins d'un étudiant spécifique
    List<Bulletin> findByEtudiantId(Long etudiantId);

    // Trouver tous les bulletins d'une classe spécifique
    List<Bulletin> findByClasseId(Long classeId);

    // Trouver tous les bulletins d'une filière spécifique
    List<Bulletin> findByFiliereId(Long filiereId);
}
