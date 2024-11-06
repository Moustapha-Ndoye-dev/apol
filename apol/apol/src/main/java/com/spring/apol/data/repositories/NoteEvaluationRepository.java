package com.spring.apol.data.repositories;

import com.spring.apol.data.models.NoteEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteEvaluationRepository extends JpaRepository<NoteEvaluation, Long> {

    // Requêtes pour trouver des notes par étudiant ou professeur
    List<NoteEvaluation> findByEtudiantId(Long etudiantId);
}
