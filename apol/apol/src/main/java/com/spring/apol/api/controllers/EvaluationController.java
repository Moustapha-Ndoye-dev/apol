package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.EvaluationDto;
import com.spring.apol.data.models.Evaluation;
import com.spring.apol.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // Get all evaluations
    @GetMapping
    public ResponseEntity<List<EvaluationDto>> getAllEvaluations() {
        List<Evaluation> evaluations = evaluationService.getAllEvaluations();
        List<EvaluationDto> evaluationDtos = evaluations.stream()
                .map(EvaluationDto::fromEntity)
                .toList();
        return ResponseEntity.ok(evaluationDtos);
    }

    // Get evaluation by ID
    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDto> getEvaluationById(@PathVariable Long id) {
        Optional<Evaluation> evaluation = evaluationService.getEvaluationById(id);
        return evaluation.map(value -> ResponseEntity.ok(EvaluationDto.fromEntity(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new evaluation
    @PostMapping
    public ResponseEntity<EvaluationDto> createEvaluation(@RequestBody EvaluationDto evaluationDto) {
        Evaluation evaluation = evaluationDto.toEntity();
        Evaluation savedEvaluation = evaluationService.saveEvaluation(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(EvaluationDto.fromEntity(savedEvaluation));
    }

    // Update evaluation
    @PutMapping("/{id}")
    public ResponseEntity<EvaluationDto> updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDto evaluationDto) {
        Optional<Evaluation> existingEvaluation = evaluationService.getEvaluationById(id);
        if (existingEvaluation.isPresent()) {
            Evaluation updatedEvaluation = evaluationDto.toEntity();
            updatedEvaluation.setId(id); // Ensure the ID is set for the update
            Evaluation savedEvaluation = evaluationService.saveEvaluation(updatedEvaluation);
            return ResponseEntity.ok(EvaluationDto.fromEntity(savedEvaluation));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete evaluation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    // Get evaluations by module ID
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<EvaluationDto>> getEvaluationsByModuleId(@PathVariable Long moduleId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByModuleId(moduleId);
        List<EvaluationDto> evaluationDtos = evaluations.stream()
                .map(EvaluationDto::fromEntity)
                .toList();
        return ResponseEntity.ok(evaluationDtos);
    }

    // Get evaluations by class ID
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<EvaluationDto>> getEvaluationsByClasseId(@PathVariable Long classeId) {
        List<Evaluation> evaluations = evaluationService.getEvaluationsByClasseId(classeId);
        List<EvaluationDto> evaluationDtos = evaluations.stream()
                .map(EvaluationDto::fromEntity)
                .toList();
        return ResponseEntity.ok(evaluationDtos);
    }
}
