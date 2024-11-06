package com.spring.apol.services.impl;

import com.spring.apol.data.models.Evaluation;
import com.spring.apol.data.repositories.EvaluationRepository;
import com.spring.apol.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServImpl implements EvaluationService {

    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationServImpl(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Optional<Evaluation> getEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public List<Evaluation> getEvaluationsByModuleId(Long moduleId) {
        return evaluationRepository.findByModule_Id(moduleId);
    }

    @Override
    public List<Evaluation> getEvaluationsByClasseId(Long classeId) {
        return evaluationRepository.findByClasses_Id(classeId);
    }
}
