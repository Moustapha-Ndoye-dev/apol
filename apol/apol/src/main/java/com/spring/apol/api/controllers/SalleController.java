package com.spring.apol.api.controllers;

import com.spring.apol.api.dto.SalleDto;
import com.spring.apol.services.SalleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/salles")
@CrossOrigin(origins = "*")
public class SalleController {

    private final SalleService salleService;

    // Injection du service
    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    // Créer une nouvelle salle
    @PostMapping
    public ResponseEntity<?> createSalle(@Valid @RequestBody SalleDto salleDto, BindingResult bindingResult) {
        // Validation des champs
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            SalleDto createdSalle = salleService.createSalle(salleDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSalle);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la création de la salle: " + e.getMessage());
        }
    }

    // Mettre à jour une salle existante
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSalle(@PathVariable Long id, @Valid @RequestBody SalleDto salleDto, BindingResult bindingResult) {
        // Validation des champs
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        SalleDto updatedSalle = salleService.updateSalle(id, salleDto);
        if (updatedSalle != null) {
            return ResponseEntity.ok(updatedSalle);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Salle non trouvée avec l'ID: " + id);
        }
    }

    // Récupérer une salle par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getSalleById(@PathVariable Long id) {
        SalleDto salleDto = salleService.getSalleById(id);
        if (salleDto != null) {
            return ResponseEntity.ok(salleDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Salle non trouvée avec l'ID: " + id);
        }
    }

    // Récupérer toutes les salles
    @GetMapping
    public ResponseEntity<List<SalleDto>> getAllSalles() {
        List<SalleDto> salles = salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

    // Supprimer une salle par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSalle(@PathVariable Long id) {
        try {
            salleService.deleteSalle(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la suppression de la salle: " + e.getMessage());
        }
    }
}
