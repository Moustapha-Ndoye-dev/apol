package com.spring.apol.services;

import com.spring.apol.data.models.Evaluation;

import java.util.List;
import java.util.Optional;

public interface EvaluationService {

    List<Evaluation> getAllEvaluations();

    Optional<Evaluation> getEvaluationById(Long id);

    Evaluation saveEvaluation(Evaluation evaluation);

    void deleteEvaluation(Long id);

    List<Evaluation> getEvaluationsByModuleId(Long moduleId);

    List<Evaluation> getEvaluationsByClasseId(Long classeId);
}
