package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.EvaluationDto;
import com.spring.apol.data.models.Evaluation;
import com.spring.apol.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // Récupérer toutes les évaluations
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllEvaluations() {
        try {
            List<Evaluation> evaluations = evaluationService.getAllEvaluations();
            List<EvaluationDto> evaluationDtos = evaluations.stream()
                    .map(EvaluationDto::fromEntity)
                    .toList();
            Map<String, Object> response = new HashMap<>();
            if (evaluationDtos.isEmpty()) {
                response.put("message", "Aucune évaluation trouvée");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 No Content
            }
            response.put("evaluations", evaluationDtos);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des évaluations");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Récupérer une évaluation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getEvaluationById(@PathVariable Long id) {
        try {
            Optional<Evaluation> evaluation = evaluationService.getEvaluationById(id);
            Map<String, Object> response = new HashMap<>();
            return evaluation.map(value -> {
                response.put("evaluation", EvaluationDto.fromEntity(value));
                return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK
            }).orElseGet(() -> {
                response.put("error", "Évaluation non trouvée");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 Not Found
            });
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération de l'évaluation");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Créer une nouvelle évaluation
    @PostMapping
    public ResponseEntity<Map<String, Object>> createEvaluation(@RequestBody EvaluationDto evaluationDto) {
        try {
            Evaluation evaluation = evaluationDto.toEntity();
            Evaluation savedEvaluation = evaluationService.saveEvaluation(evaluation);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Évaluation créée avec succès");
            response.put("evaluation", EvaluationDto.fromEntity(savedEvaluation));
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la création de l'évaluation");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Mettre à jour une évaluation
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDto evaluationDto) {
        try {
            Optional<Evaluation> existingEvaluation = evaluationService.getEvaluationById(id);
            Map<String, Object> response = new HashMap<>();
            return existingEvaluation.map(evaluation -> {
                Evaluation updatedEvaluation = evaluationDto.toEntity();
                updatedEvaluation.setId(id); // Assurer que l'ID est défini pour la mise à jour
                Evaluation savedEvaluation = evaluationService.saveEvaluation(updatedEvaluation);
                response.put("message", "Évaluation mise à jour avec succès");
                response.put("evaluation", EvaluationDto.fromEntity(savedEvaluation));
                return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK
            }).orElseGet(() -> {
                response.put("error", "Évaluation non trouvée");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 Not Found
            });
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la mise à jour de l'évaluation");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Supprimer une évaluation
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteEvaluation(@PathVariable Long id) {
        try {
            evaluationService.deleteEvaluation(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Évaluation supprimée avec succès");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 No Content
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression de l'évaluation");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Récupérer les évaluations par ID de module
    @GetMapping("/module/{moduleId}")
    public ResponseEntity<Map<String, Object>> getEvaluationsByModuleId(@PathVariable Long moduleId) {
        try {
            List<Evaluation> evaluations = evaluationService.getEvaluationsByModuleId(moduleId);
            List<EvaluationDto> evaluationDtos = evaluations.stream()
                    .map(EvaluationDto::fromEntity)
                    .toList();
            Map<String, Object> response = new HashMap<>();
            if (evaluationDtos.isEmpty()) {
                response.put("message", "Aucune évaluation trouvée pour ce module");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 No Content
            }
            response.put("evaluations", evaluationDtos);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des évaluations du module");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }

    // Récupérer les évaluations par ID de classe
    @GetMapping("/classe/{classeId}")
    public ResponseEntity<Map<String, Object>> getEvaluationsByClasseId(@PathVariable Long classeId) {
        try {
            List<Evaluation> evaluations = evaluationService.getEvaluationsByClasseId(classeId);
            List<EvaluationDto> evaluationDtos = evaluations.stream()
                    .map(EvaluationDto::fromEntity)
                    .toList();
            Map<String, Object> response = new HashMap<>();
            if (evaluationDtos.isEmpty()) {
                response.put("message", "Aucune évaluation trouvée pour cette classe");
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT); // 204 No Content
            }
            response.put("evaluations", evaluationDtos);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 OK
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des évaluations de la classe");
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); // Handle errors
        }
    }
}
