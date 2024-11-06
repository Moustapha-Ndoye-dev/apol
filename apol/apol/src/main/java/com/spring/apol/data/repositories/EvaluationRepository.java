package com.spring.apol.data.repositories;

import com.spring.apol.data.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    // Custom query methods can be added here if needed

    // Example: Find evaluations by module ID
    List<Evaluation> findByModule_Id(Long moduleId);

    // Example: Find evaluations by class ID
    List<Evaluation> findByClasses_Id(Long classeId);
}
