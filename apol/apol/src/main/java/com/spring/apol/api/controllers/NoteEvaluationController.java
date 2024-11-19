package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.NoteEvaluationDto;
import com.spring.apol.services.NoteEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/note-evaluations")
public class NoteEvaluationController {

    private final NoteEvaluationService noteEvaluationService;

    @Autowired
    public NoteEvaluationController(NoteEvaluationService noteEvaluationService) {
        this.noteEvaluationService = noteEvaluationService;
    }

    // Endpoint pour créer une note d'évaluation
    @PostMapping("/{professeurId}")
    public ResponseEntity<?> createNoteEvaluation(
            @RequestBody NoteEvaluationDto noteEvaluationDto,
            @PathVariable Long professeurId,
            BindingResult bindingResult) {

        // Validation des entrées
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            NoteEvaluationDto createdNoteEvaluation = noteEvaluationService.save(noteEvaluationDto, professeurId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNoteEvaluation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de la note d'évaluation: " + e.getMessage());
        }
    }

    // Endpoint pour mettre à jour une note d'évaluation
    @PutMapping("/{id}/{professeurId}")
    public ResponseEntity<?> updateNoteEvaluation(
            @PathVariable Long id,
            @RequestBody NoteEvaluationDto noteEvaluationDto,
            @PathVariable Long professeurId,
            BindingResult bindingResult) {

        // Validation des entrées
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            NoteEvaluationDto updatedNoteEvaluation = noteEvaluationService.update(id, noteEvaluationDto, professeurId);
            if (updatedNoteEvaluation != null) {
                return ResponseEntity.ok(updatedNoteEvaluation);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Note d'évaluation non trouvée avec l'ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour de la note d'évaluation: " + e.getMessage());
        }
    }

    // Endpoint pour supprimer une note d'évaluation
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNoteEvaluation(@PathVariable Long id) {
        try {
            noteEvaluationService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de la note d'évaluation: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir une note d'évaluation par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getNoteEvaluationById(@PathVariable Long id) {
        try {
            NoteEvaluationDto noteEvaluationDto = noteEvaluationService.findById(id);
            if (noteEvaluationDto != null) {
                return ResponseEntity.ok(noteEvaluationDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Note d'évaluation non trouvée avec l'ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération de la note d'évaluation: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir toutes les notes d'évaluation
    @GetMapping
    public ResponseEntity<?> getAllNoteEvaluations() {
        try {
            List<NoteEvaluationDto> noteEvaluations = noteEvaluationService.findAll();
            return ResponseEntity.ok(noteEvaluations);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des notes d'évaluation: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir les notes d'évaluation par ID d'étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<?> getNoteEvaluationsByEtudiantId(@PathVariable Long etudiantId) {
        try {
            List<NoteEvaluationDto> noteEvaluations = noteEvaluationService.findByEtudiantId(etudiantId);
            if (noteEvaluations.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucune note d'évaluation trouvée pour l'étudiant avec l'ID: " + etudiantId);
            }
            return ResponseEntity.ok(noteEvaluations);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des notes d'évaluation de l'étudiant: " + e.getMessage());
        }
    }
}
