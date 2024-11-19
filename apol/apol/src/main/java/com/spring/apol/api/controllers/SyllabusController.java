package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.SyllabusDto;
import com.spring.apol.services.SyllabusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/syllabi") // URL de base pour les endpoints de syllabus
public class SyllabusController {

    private final SyllabusService syllabusService;

    public SyllabusController(SyllabusService syllabusService) {
        this.syllabusService = syllabusService;
    }

    // Endpoint pour créer un syllabus
    @PostMapping
    public ResponseEntity<?> createSyllabus(@RequestBody SyllabusDto syllabusDto, BindingResult bindingResult) {
        // Validation des champs
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            SyllabusDto createdSyllabus = syllabusService.createSyllabus(syllabusDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSyllabus);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création du syllabus: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir un syllabus par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSyllabusById(@PathVariable Long id) {
        try {
            SyllabusDto syllabusDto = syllabusService.getSyllabusById(id);
            if (syllabusDto != null) {
                return ResponseEntity.ok(syllabusDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Syllabus non trouvé avec l'ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération du syllabus: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir tous les syllabi
    @GetMapping
    public ResponseEntity<String> getAllSyllabi() {
        try {
            List<SyllabusDto> syllabi = syllabusService.getAllSyllabi();
            return ResponseEntity.ok(syllabi.toString());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des syllabi: " + e.getMessage());
        }
    }

    // Endpoint pour mettre à jour un syllabus
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSyllabus(@PathVariable Long id, @RequestBody SyllabusDto syllabusDto, BindingResult bindingResult) {
        // Validation des champs
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            SyllabusDto updatedSyllabus = syllabusService.updateSyllabus(id, syllabusDto);
            if (updatedSyllabus != null) {
                return ResponseEntity.ok(updatedSyllabus);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Syllabus non trouvé avec l'ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la mise à jour du syllabus: " + e.getMessage());
        }
    }

    // Endpoint pour supprimer un syllabus
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSyllabus(@PathVariable Long id) {
        try {
            syllabusService.deleteSyllabus(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression du syllabus: " + e.getMessage());
        }
    }

    // Endpoint pour obtenir les syllabi par ID de professeur
    @GetMapping("/professeur/{professeurId}")
    public ResponseEntity<?> getSyllabiByProfesseurId(@PathVariable Long professeurId) {
        try {
            List<SyllabusDto> syllabi = syllabusService.getSyllabiByProfesseurId(professeurId);
            if (syllabi.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucun syllabus trouvé pour le professeur avec l'ID: " + professeurId);
            }
            return ResponseEntity.ok(syllabi);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la récupération des syllabi du professeur: " + e.getMessage());
        }
    }

    // Endpoint pour rechercher des syllabi par titre
    @GetMapping("/search")
    public ResponseEntity<?> searchSyllabiByTitle(@RequestParam String titre) {
        try {
            List<SyllabusDto> syllabi = syllabusService.searchSyllabiByTitle(titre);
            if (syllabi.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Aucun syllabus trouvé pour le titre: " + titre);
            }
            return ResponseEntity.ok(syllabi);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la recherche des syllabi: " + e.getMessage());
        }
    }
}
